# Práctica JEE - Raquel Díaz González

## Persistencia

### A1) Modificar la capa de persistencia para que los tokens caduquen en hora
  - Añadido atributo ```creationDate``` a la clase ```Token```
  - Añadidos métodos ```get``` y ```set``` para el nuevo atributo creado y modificado método ```toString```
  - Creado método ```isValid``` en la clase Token para comprobar si un token es válido

### A2) Se deberá ofrecer una funcionalidad de eliminación de tokens caducados
  - Creada interfaz ```TokenDaoExtended```
  - Modificada interfaz ```TokenDao``` para que herede de la interfaz creada anteriormente 
  - Creada clase ```TokenDaoImpl``` para alojar los métodos personalizados 
  - Creado método ```removeExpiredTokens``` en la clase anteriormente creada para eliminar todos los tokens caducados de la bbdd 

### B) Ampliar la capa de persistencia para poder ofrecer un servicio de clases de padel
  - Creada clase ```Training``` con los atributos id, fecha de inicio y fecha de fin, relación ManyToOne con la clase Court, relación ManyToOne con la clase User (entrenador asociado) y relación ManyToMany con la clase User (listado de alumnos asociados). 
  - Los usuarios podrán apuntarse a las diferentes clases, con un máximo de cuatro alumnos por clase. El método ```addUser``` de la clase Training permite añadir un usuario a la clase teniendo en cuenta que solo puede haber 4 alumnos. Si se intenta añadir otro alumno cuando la clase ya tiene 4, éste no se insertará.
  - Creada interfaz ```TrainingDao ```
  - Creada interfaz ```TrainingDaoExtended``` 
  - Creada clase ```TrainingDaoImpl``` 
  - El entrenador podrá crear clases de padel, de una hora a la semana, con una fecha de inicio y una de final, asociados a una pista. Creado método ```createTraining```. Indicando un rango de fechas y una pista, se creará el entrenamiento y las reservas necesarias en ese rango de fechas teniendo en cuenta que solo puede haber 1 reserva por semana. En caso de que haya una reserva, esta será eliminada para poder añadir la clase de entrenamiento. (se ha considerado que tienen más prioridad las clases de entrenamiento). 
  - Los usuarios podrán consultar las clases. (métodos ```findTrainingsByCourt``` y ```findTrainingByStartDateAndFinishDateAndCourt```). Se pueden consultar todas las clases, las que tienen una pista determinada o hacer una búsqueda por fecha de inicio, fecha de fin y pista. 
  - Los usuarios podrán apuntarse a las diferentes clases. Creado método ```registerTrainingPlayer```
  - El entrenador podrá eliminar un alumno de la clase. Creado método ```deleteTrainingPlayer``` 
  - El entrenador podrá borrar dicha clase. Creado método ```deleteTraining```

### C) Modificar o realizar los test de las mejorar anteriores
  - Creado ```testTokenIsValid``` en la clase TokenTest para probar el apartado A1 
  - Creado ```testRemoveExpiredTokens``` en la clase ```TokenDaoITest``` para probar el apartado A2
  - Creada clase ```TrainingDaoITest``` con todos los tests para probar el apartado B Api 

## Api

### A) Modificar la capa de negocio para que la validación de tokens incluya la mejora de caducidad 
  - Creada interfaz ```UserDaoExtended ```
  - Creada clase ```UserDaoImpl``` 
  - Se mantiene el método ```findByTokenValue``` de la interfaz UserDao aunque no se utiliza 
  - Creado método ```findByTokenValueValid``` en la clase ```UserDaoExtended```
  - Implementacion del método ```findByTokenValueValid``` en la clase ```UserDaoImpl```, donde se comprueba que el token utilizado para hacer login sea válido y además no esté caducado 
  - En la clase ```UserDetailsServiceImpl``` se sustituye la llamada a ```findByTokenValue``` por ```findByTokenValueValid``` 

### B) Ampliar la capa de negocio para poder ofrecer un servicio de clases de padel, incluyendo la seguridad. 
  - Creado controlador ```TrainingController```
  - Creado controlador ```TrainingControlleroCreados``` métodos ```showTrainings```, ```createTraining```, ```deleteTraining```, ```registerTrainingPlayer```, ```deleteTrainingPlayer``` y ```existTraining```
  - Añadida constante ```TRAININGS``` en la clase ```Uris``` para indicar la ruta de las operaciones relacionadas con los entrenamientos (/trainings) 
  - Añadidas constantes ```USER_ID``` y ```TRAINING_ID```  en la clase ```Uris``` para las rutas con varios ids (/trainings/{trainingId}/users/{userId})
  - Creada clase ```TrainingResource``` que contiene todos los métodos de la API para los entrenadores 
  - Para la seguridad 
    - En ```SecurityConfig``` se añade el ```rol trainer``` en todas las urls /trainings  
    - Creado método ```createDefaultTrainer``` en DataService para crear un entrenador por defecto que usa la clase Populate
    - Modificado el archivo ```application.properties``` para tener los datos del entrenador
    - En ```RestService``` se añade el método ```loginTrainer``` para hacer login con un entrenador
    - En ```RestService``` se añade el método ```registerPlayer ```
    - En ```RestService``` se añade el método ```createTraining ```

### C) Modificar o realizar los test de las mejorar anteriores 
  - Creada clase ```TrainingResourceFunctionalTesting``` con todos los test para probar los métodos de la api 

## Web

### A) Realizar la capa Web de algunas de las operaciones de la aplicación de Paddle, sin seguridad 
  - En la carpeta ```/webapp/WEB-INF/views``` están todas las vistas jsp
  - Añadido bootstrap en las vistas
  - Creado un head y un footer que se importaran en todas las vistas con el objetivo de evitar la duplicidad de código 
  - Creado un menú que se importará en algunas vistas
  - ```Courts```
    - Vistas: court-list y create-court 
    - Operaciones: Ver todas, crear una nueva, activar y desactivar
  - ```User```
    - Vistas: user-list y create-user
    - Operaciones: Ver todos y crear uno nuevo
    - Para que la fecha se muestre correctamente hay que formatearla con una etiqueta JSTL
    - Para que el campo fecha se guarde correctamente se ha añadido una anotación en el campo birthDate de la clase UserWrapper 
    - Se muestra el rol de cada usuario 
      - Creado AuthorizationController con un método para recuperar todos los roles asociados a los usuarios
  - ```Training``` 
    - Vistas: training-list y create-training
    - Operaciones: ver todos, crear uno nuevo y eliminar uno existente 
    - Para que la fecha se muestre correctamente hay que formatearla con una etiqueta JSTL 
    - Para que el campo fecha se guarde correctamente se ha añadido una anotación en el campo startDate y finishDate de la clase TrainingWrapper 
  - ```OperationOk``` para mostrar una página de realimentación
