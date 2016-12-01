# Start up del backend

```
`mvn clean package`
```

para generar el WAR y correrlo desde un conteiner web, o directamente desde eclipse
**Nota: ** Puerto por defecto 8080, si se cambia el puerto, cambiar también el archivo de `environment.js` en el frontend

# Start up del frontend

Cualquier file server. Ejemplo: Apache, nginx, tomcat, etc. Yo uso live-server( npm install live-server -g ) y se levanta desde consola, ubicado en la carpeta **/app** del proyecto y ejecutando `live-server --port=8181`

**Nota:** si eligen otro puerto para el server del frontend hay que cambiar la configuracion del filter de cors en la clase WebMvcConfig.java

**Nota 2:** recuerden instalar el plugin de cors para chrome si quieren ver la cartelera con las imágenes. <https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi>
