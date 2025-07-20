## Initial setup

```
cd ~/dev/personal
mkdir budget-app
cd budget-app
mkdir budget-backend
mkdir budget-frontent
```

## Backend
```
cd budget-backend

curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,postgresql \
  -d type=maven-project \
  -d language=kotlin \
  -d name=budget-backend \
  -d groupId=com.example \
  -d artifactId=budget-backend \
  -d packageName=com.example.budget \
  -o budget-backend.zip

unzip budget-backend.zip
```
## Frontent
```
npm create vite@latest budget-frontend -- --template react
cd budget-frontend
npm install
```
### Axios
```
npm install axios
```
