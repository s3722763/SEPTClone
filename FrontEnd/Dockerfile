FROM node:12-alpine

COPY project-app/package.json /build/
COPY project-app/src /build/src/

WORKDIR /build/

RUN npm install

EXPOSE 8080
ENTRYPOINT ["npm", "start"]