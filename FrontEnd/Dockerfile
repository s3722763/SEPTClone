FROM node:12-alpine

COPY project-app/package.json /build/
COPY project-app/src /build/src/
COPY project-app/public /build/public

WORKDIR /build/

RUN npm install --production

EXPOSE 3000
ENTRYPOINT ["npm", "start"]