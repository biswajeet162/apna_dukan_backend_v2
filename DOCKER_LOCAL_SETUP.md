# Docker Local Setup Guide

## Prerequisites
- Docker Desktop installed and running
- Git repository cloned locally

## Step 1: Build the Docker Image dfg

Open terminal/command prompt in your project root directory and run:

```bash
docker build -t apna-dukan-backend:latest .
```

This will:
- Build the Maven project
- Create a Docker image named `apna-dukan-backend:latest`
- Take 2-5 minutes on first build (subsequent builds are faster due to caching)

## Step 2: Run the Docker Container

### Option A: Run with Production Profile (Default Port 8080)

```bash
docker run -d -p 8080:8080 --name apna-dukan-backend apna-dukan-backend:latest
```

### Option B: Run with Custom Port

```bash
docker run -d -p 8080:8080 -e PORT=8080 --name apna-dukan-backend apna-dukan-backend:latest
```

### Option C: Run with Local Profile (Uses application.yaml instead of prod)

First, modify the Dockerfile ENTRYPOINT temporarily, or use:

```bash
docker run -d -p 8080:8080 -e PORT=8080 -e SPRING_PROFILES_ACTIVE=default --name apna-dukan-backend apna-dukan-backend:latest
```

## Step 3: Verify Container is Running

```bash
docker ps
```

You should see your container listed with status "Up".

## Step 4: Check Logs

```bash
docker logs apna-dukan-backend
```

Or follow logs in real-time:
```bash
docker logs -f apna-dukan-backend
```

## Step 5: Test the Application

Once you see "Started ApnaDukanBackendApplication" in the logs, test:

- **Health Endpoint**: http://localhost:8080/api/health
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs

## Useful Docker Commands

### Stop the Container
```bash
docker stop apna-dukan-backend
```

### Start the Container (if stopped)
```bash
docker start apna-dukan-backend
```

### Remove the Container
```bash
docker rm apna-dukan-backend
```

### Remove the Image
```bash
docker rmi apna-dukan-backend:latest
```

### View Container Logs
```bash
docker logs apna-dukan-backend
```

### Execute Commands Inside Container
```bash
docker exec -it apna-dukan-backend sh
```

### View Container Resource Usage
```bash
docker stats apna-dukan-backend
```

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, use a different port:
```bash
docker run -d -p 9090:8080 -e PORT=8080 --name apna-dukan-backend apna-dukan-backend:latest
```
Then access at: http://localhost:9090

### Container Keeps Restarting
Check logs for errors:
```bash
docker logs apna-dukan-backend
```

### Rebuild After Code Changes
```bash
# Stop and remove old container
docker stop apna-dukan-backend
docker rm apna-dukan-backend

# Rebuild image
docker build -t apna-dukan-backend:latest .

# Run again
docker run -d -p 8080:8080 -e PORT=8080 --name apna-dukan-backend apna-dukan-backend:latest
```

### Clean Up Everything
```bash
# Stop and remove container
docker stop apna-dukan-backend
docker rm apna-dukan-backend

# Remove image
docker rmi apna-dukan-backend:latest

# Optional: Clean up all unused Docker resources
docker system prune -a
```

## Database Persistence

The H2 database file is stored inside the container at `/app/data/apnadukan.mv.db`.

To persist data across container restarts, use a volume:

```bash
docker run -d -p 8080:8080 -e PORT=8080 \
  -v apna-dukan-data:/app/data \
  --name apna-dukan-backend \
  apna-dukan-backend:latest
```

This creates a Docker volume named `apna-dukan-data` that persists even if you remove the container.


