# Distributed Computation of Persistence

## Project Description
The "Distributed Computation of Persistence" project aims to develop a tool for managing a network of machines. The network consists of three main groups of machines: workers, servers, and clients.

### Workers
The workers in the network are responsible for performing computations. They receive tasks and execute them, producing computation results.

### Servers
The servers receive the computation results from the workers and handle their organization and storage. The results can be stored either in RAM or on disk. Additionally, the servers can perform statistics on the stored results.

### Clients
The clients are part of the network and can connect to the servers. They have access to the database maintained by the servers, allowing them to query specific ranges of results and request various types of statistics from the servers.

## Project Structure
The project is developed in Java and organized into separate packages for each group of machines. The following folders are included:

### client
This folder contains the code for the client software. It provides the necessary functionality to connect to the servers and access the database. Clients can query results and request statistics from the servers.

### config
The config folder holds configuration files and settings for the entire system. It includes any necessary configuration files for the workers, servers, and clients.

### server
The server folder contains the code for the server software. It receives the computation results from the workers, stores them appropriately (in RAM or on disk), and performs statistics on the results.

### test
The test folder includes test cases and unit tests for the project. It ensures the correctness and reliability of the implemented functionality.

### worker
The worker folder contains the code for the worker software. It handles the computations required by the project. Each worker executes assigned tasks and produces computation results.

## Installation and Setup
To use the "Distributed Computation of Persistence" project, follow these steps:

1. Clone the project repository from [GitHub](https://github.com/your-repository-url).
2. Install Java on your machine if it is not already installed.
3. Configure the project by modifying the necessary settings and configuration files in the config folder.
4. Compile the Java source files in each package using a Java compiler.
5. Run the necessary components on the machines designated as workers, servers, and clients.

## Usage
To utilize the functionality of the "Distributed Computation of Persistence" project, follow these instructions:

1. Ensure that the workers, servers, and clients are correctly set up and running.
2. Connect a client to a server using the network.
3. Use the client software to access the database and query specific ranges of results.
4. Request desired statistics from the server.
5. Analyze and interpret the received results and statistics.

## Authors
- AHMIM Mohamed
- RAMDANI Ferhat
