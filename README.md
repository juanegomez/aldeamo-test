# Guía para Ejecutar el API para la prueba tecnica de Aldeamo

## Prerequisitos

- **Java JDK 17** instalado en tu máquina.
- **IntelliJ IDEA** para desarrollar y ejecutar la aplicación.
- **Docker** y **Docker Compose** instalados para gestionar los contenedores de la base de datos.

## 1. Configuración del Proyecto en IntelliJ IDEA

### 1.1. Clona el Repositorio

Si aún no tienes el código fuente, clónalo desde el repositorio de Git:

```bash
git clone https://github.com/juanegomez/aldeamo-test.git
cd aldeamo-test
```

### 1.2. Importa el Proyecto en IntelliJ IDEA

1. Abre IntelliJ IDEA.
2. Selecciona **File > Open** y navega a la carpeta del proyecto.
3. Selecciona la carpeta del proyecto y haz clic en **OK**.
4. IntelliJ IDEA debería detectar automáticamente el archivo `build.gradle` y configurar el proyecto.

## 2. Ejecución de Contenedores Docker

1. Abre una terminal en la raíz del proyecto (donde se encuentra el archivo `docker-compose.yml`).

2. Ejecuta el siguiente comando para levantar los contenedores:

    ```bash
    docker-compose up
    ```

   Este comando descargará las imágenes necesarias (si no están ya disponibles), creará los contenedores y los iniciará.

3. Verifica que los servicios estén funcionando:
    - Adminer estará disponible en [http://localhost:8081](http://localhost:8081) para gestionar la base de datos MySQL.

4. Para detener los contenedores, presiona `Ctrl + C` en la terminal donde ejecutaste `docker-compose up`. Si deseas eliminar los contenedores, ejecuta:

    ```bash
    docker-compose down
    ```

## 3. Ejecutar la Aplicación desde IntelliJ IDEA

Una vez que hayas configurado el proyecto en IntelliJ IDEA y levantado los contenedores Docker, sigue estos pasos para ejecutar la aplicación Spring Boot desde IntelliJ IDEA:

1. **Abre IntelliJ IDEA** y asegúrate de que el proyecto esté abierto y correctamente importado.

2. **Configura el archivo de ejecución**:
    - En la parte superior derecha de IntelliJ IDEA, selecciona el menú desplegable de configuración de ejecución (a menudo muestra el nombre del proyecto).
    - Selecciona **Edit Configurations**.
    - Asegúrate de que la configuración de **Spring Boot** esté creada. Si no, haz clic en el botón **+** para añadir una nueva configuración de tipo **Spring Boot**.
    - En **Main class**, selecciona la clase principal de tu aplicación que tiene la anotación `@SpringBootApplication` (por defecto, suele ser `com.aldeamo.test.TestAldeamoApplication` o similar).

3. **Ejecuta la aplicación**:
    - Selecciona la configuración de **Spring Boot** que acabas de crear o verificar.
    - Haz clic en el botón de **Run** (el ícono de un triángulo verde) para iniciar la aplicación.

   La aplicación debería iniciarse y estar disponible en [http://localhost:8090](http://localhost:8090).

## 4. Realizar Prueba al Endpoint de la prueba

Para probar el endpoint del ejercicio `test-aldeamo/api/bartender/exercise`, sigue estos pasos:

1. **Verifica que la aplicación esté corriendo** en [http://localhost:8090](http://localhost:8090).

2. **Prueba el endpoint** con los parámetros de consulta adecuados usando una herramienta como Postman o `cURL`. El endpoint requiere dos parámetros de consulta:

   - `iterations`: El número de iteraciones que se deben realizar.
   - `arrayId`: El ID del arreglo de vasos del bartender.

   Por ejemplo, puedes probar el endpoint con `cURL` de la siguiente manera:

   ```bash
   curl "http://localhost:8090/test-aldeamo/api/bartender/exercise?iterations=10&arrayId=1"

3. **Verifica la respuesta** que obtienes en la terminal o en la herramienta de prueba que estés usando. Asegúrate de que la respuesta sea la esperada y que los datos devueltos sean correctos.

Con estos pasos, deberías poder realizar una prueba efectiva del endpoint y verificar que la aplicación está funcionando correctamente. Si encuentras algún problema, revisa los logs de la aplicación para obtener más información sobre posibles errores.