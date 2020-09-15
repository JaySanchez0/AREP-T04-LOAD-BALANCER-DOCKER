# AREP-T04-LOAD-BALANCER-DOCKER

### Presentado por:

Jeisson G. Sanchez R.


### Docker Repository

[ver](https://hub.docker.com/r/jsanchez0/app-load-balancer)

### Build

~~~
 docker build  --tag jsanchez0/app-load-balancer:latest .
~~~

### Download 

~~~
    docker pull --tag jsanchez0/app-load-balancer
~~~

### Run

~~~
    docker run -dp 80:80 -e PORT=80 jsanchez0/app-load-balancer
~~~

### Deploy

[ver](http://ec2-100-25-103-0.compute-1.amazonaws.com/)