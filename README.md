# Keda-Spring
 Implementing Kubernetes Event Driven Auto Scaling with Keda, Spring and RabbitMQ
 
## Keda

KEDA is a single-purpose and lightweight component that can be added into any Kubernetes cluster. KEDA works alongside standard Kubernetes components like the Horizontal Pod Autoscaler and can extend functionality without overwriting or duplication. With KEDA you can explicitly map the apps you want to use event-driven scale, with other apps continuing to function. This makes KEDA a flexible and safe option to run alongside any number of other Kubernetes applications or frameworks.

Autoscaling is one the favorite features of Kubernetes. When we talk about Kubernetes autoscaling features, then the Horizontal Pod Autoscaler (HPA) will automatically come into mind. HPA is responsible for scaling of pods based on CPU or RAM usage.

Complex and distributed applications which integrate with different components outside of the containers. Those components are basically event sources and can be anything like MongoDB, Azure Service Bus, Length of RabbitMQ queue, Kafka topic lag, Redis Stream, etc. The HPA is not able to scale the pods based on metrics from these components. To mitigate this kind of situation, KEDA comes with extended functionalities on top of the Horizontal Pod Autoscaler. With KEDA you can explicitly map the apps you want to use event-driven scale, with other apps continuing to function. It also allows you to integrate a Scaler into your Kubernetes cluster. It will help you to scale your pods based on the different metrics from those event sources.
KEDA is a single-purpose and lightweight component that can be added into any Kubernetes cluster. KEDA works alongside standard Kubernetes components like the Horizontal Pod Autoscaler and can extend functionality without overwriting or duplication.

## Architecture of KEDA

The principal layer in KEDA architecture is:

- Operator/Agent: Activates and deactivates Kubernetes Deployments to scale to and from zero on no events.
- Scalers: Connects to an external event source and feeds custom metrics for a specific event source. A current list of scalers is available on the KEDA home page.
- Metrics: Acts as a Kubernetes metrics server that exposes rich event data like queue length or stream lag to the Horizontal Pod Autoscaler to drive scale out.

![Keda Architecture](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/6raoo7jceo332nozvdqm.png)

## Running and Implementing Keda

The following technologies were used to carry out the project and it is necessary to install some items:

- Docker
- Java 17
- Maven
- SpringBoot
- Kubernetes(Minikube)
- RabbitMQ


![Architecture of Application using Keda in Kubernetes](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/teg0tc4i85ft17ck412s.png)

### Preparing Environment

**Install Keda in Kubernetes Cluster**

Keda is a solution for running in kubernetes cluster. Use minikube to start our cluster. Follow the command below to start the kubernetes cluster:

```
minikube start
```

After running the command, you need another command to install Keda in our cluster, follow the command:

```
kubectl apply -f https://github.com/kedacore/keda/releases/download/v2.0.0/keda-2.0.0.yaml
```

You used this command for consulting your installation of Keda:
```
kubectl get all -n keda
```

**Installing RabbitMQ in Kubernetes Cluster**

RabbitMQ improves Operator approach. For that I have used the RabbitMQ cluster operator. Follow the command to install RabbitMQ in your cluster:

```
kubectl apply -f https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml
```

For you check RabbitMQ deployment on the cluster:

```
kubectl get all -n rabbitmq-system
```

In the project ***event-service-sender***, you have the k8s path and you are running the command for configure Rabbit:

```
kubectl apply -f k8s/rabbitmq-keda-cluster.yaml
```

For you to verify Replica and All Configuration to the Rabbit:

```
kubectl describe RabbitmqCluster rabbitmq-keda-cluster
kubectl get all -l app.kubernetes.io/part-of=rabbitmq
```

To finalize the rabbit management in the localhost, you need to execute this command:

```
kubectl port-forward service/rabbitmq-keda-cluster 15672
```

Now you can access this endpoint http://localhost:15672 using User:guest and Password: guest to control your RabbitMQ and Create your Queue.

### Deployment Publisher and Consumer in Kubernetes

After you configure Kubernetes cluster and Rabbit, our environment is ready for running our applications and proof of concept with Keda.

Four you deploy Event Sender and Event Receiver, you need navigate on terminal to the path for both project and access the  you need execute this command:

```
kubectl apply -f k8s/app-deployment.yaml
```

Four you deploy the Event Receiver you need execute this command:

```
kubectl apply -f k8s/app-deployment.yaml
```

You need consulting pods for both services:

```
kubectl get pods
```

For enabling localhost to both applications running the two commands in two different instances to the terminal:

```
kubectl port-forward svc/event-service-receiver 9095
kubectl port-forward svc/event-service-sender 9096
```

Now, you can use jmeter for stress test and you follow the scalability in your consumer service


