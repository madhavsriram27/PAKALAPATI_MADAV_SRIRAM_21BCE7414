# PAKALAPATI MADAV SRIRAM 21BCE7414
# Chess Game Project

## Overview

This repository contains a chess game application. The server is built using Java and WebSocket, while the client is built using JavaScript. This README provides setup and run instructions for both the server and client.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
  - [Server Setup](#server-setup)
  - [Client Setup](#client-setup)
- [Running the Project](#running-the-project)
  - [Running the Server](#running-the-server)
  - [Running the Client](#running-the-client)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

### Server

- [Java 8+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.6+](https://maven.apache.org/download.cgi)

### Client

- [Node.js](https://nodejs.org/) (for npm)
- A web browser (for accessing the client)

## Setup Instructions

### Server Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/madhavsriram27/PAKALAPATI_MADAV_SRIRAM_21BCE7414.git
   cd PAKALAPATI_MADAV_SRIRAM_21BCE7414/server
   ```

2. **Build the Server**
   ```bash
   mvn clean install
   ```

### Client Setup

1. **Navigate to the Client Directory**
   ```bash
   cd ../client
   ```

2. **Install Dependencies**
   ```bash
   npm install
   ```

## Running the Project

### Running the Server

1. **Start the Server**
   ```bash
   cd server
   mvn exec:java -Dexec.mainClass="com.example.GameServer"
   ```

### Running the Client

1. **Start the Client**
   ```bash
   cd client
   npm start
   ```
