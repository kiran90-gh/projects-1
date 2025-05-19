# iDreamApp – Spring Boot Microservices with Kubernetes Ingress on GCP

This project demonstrates how to deploy a multi-service Spring Boot application on **Google Kubernetes Engine (GKE)** using **Ingress** for routing, built and pushed using **Podman** from a **CentOS GCP VM Instance**.

---

## 🧰 Prerequisites

- GCP Project with billing enabled
- GKE cluster and kubectl configured
- GCP VM (CentOS) instance with Docker/Podman
- Docker Hub or Red Hat registry access

---

## 🔧 Technologies Used

- Java 21 (Oracle JDK)
- Maven
- Spring Boot
- Docker/Podman
- Kubernetes (GKE)
- NGINX Ingress Controller
- GCP VM (CentOS)
- GitHub

---

## 🚀 Setup on GCP VM (CentOS)

```bash
# System Update & Basic Tools
sudo yum update -y
sudo yum install wget tar -y

# Install JDK 21
wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz
tar -xzf jdk-21_linux-x64_bin.tar.gz
sudo mv jdk-21.0.7 /usr/lib/jvm/jdk-21

# Set Java environment variables
-----------------------------------
sudo tee /etc/profile.d/jdk21.sh > /dev/null <<EOF
export JAVA_HOME=/usr/lib/jvm/jdk-21
export PATH=\$JAVA_HOME/bin:\$PATH
EOF

source /etc/profile.d/jdk21.sh
java -version

# Install Maven, Git
sudo yum install maven git -y
mvn -version
git --version

# Clone the Repository & Build the App
----------------------------------------
git clone https://github.com/sssbabu/Demo2.git
cd Demo2/
mvn clean install

# Install Docker/Podman
sudo yum install docker -y

# or use Podman if Docker isn't available
podman login registry.redhat.io

# Create Dockerfile if needed and build image
podman build -t satyadocckerhub/demo2-app:latest .

# Push image to Docker Hub
---------------------------
podman login docker.io
podman push docker.io/satyadocckerhub/demo2-app:latest

Deploying to GKE
----------------------
Step 1: Deploy Ingress Controller
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.9.0/deploy/static/provider/cloud/deploy.yaml
Step 2: Deploy Your Application

kubectl create -f deployment-idream1.yaml 
kubectl create -f deployment-idream2.yaml 
kubectl create -f deployment-idream3.yaml 
kubectl create -f service-idream1.yaml 
kubectl create -f service-idream2.yaml 
kubectl create -f service-idream3.yaml 
kubectl create -f idream-ingress.yaml 

🔍 Verification
----------------------
Get Ingress External IP:
----------------------------
kubectl get svc -n ingress-nginx

Get Details:
-----------------
kubectl get ingress idream-ingress
kubectl get svc idream1-service -o yaml
kubectl get pods -l app=idream1
🌐 Access the App
=====================
🖥 On Windows
--------------
Open C:\Windows\System32\drivers\etc\hosts as Administrator.

Add the following line:
-------------------------
<EXTERNAL-IP>    idream.demo.com
Replace <EXTERNAL-IP> with the Ingress IP from:

kubectl get svc -n ingress-nginx

🔗 Open in Browser
-------------------
http://idream.demo.com/idream1

http://idream.demo.com/idream2

http://idream.demo.com/idream3

🧪 Test with curl
--------------------------
curl -H "Host: idream.demo.com" http://<EXTERNAL-IP>/idream1

🧱 Endpoints
----------------
Path	Response
/idream1	Hello from Development!
/idream2	Hello from Testing!
/idream3	Hello from Devops!

📂 Project Structure
-------------------------
Demo2/
├── src/
├── Dockerfile
├── deployment-idream1.yaml
├── deployment-idream2.yaml
├── deployment-idream3.yaml
├── service-idream1.yaml
├── service-idream2.yaml
├── service-idream3.yaml
├── idream-ingress.yaml
└── README.md

👤 Author
Satyasarathi
Java & DevOps Engineer
Docker Hub: satyadocckerhub
