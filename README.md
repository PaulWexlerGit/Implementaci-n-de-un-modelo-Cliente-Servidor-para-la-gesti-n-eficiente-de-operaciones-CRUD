En el presente Trabajo Final de Grado, se busca abordar la optimización de las operaciones sobre bases de datos utilizando las API RESTful como una alternativa eficiente. 
El objetivo principal de este proyecto es simplificar la implementación de API RESTful en el servidor, aumentando así la productividad en el desarrollo de aplicaciones. 
Esto se debe a que las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las bases de datos suelen compartir una cantidad considerable de código repetitivo. 
  
Para profundizar en el funcionamiento del servidor, es crucial entender que este será capaz de procesar solicitudes desde un cliente y gestionarlas de manera eficiente 
mediante operaciones transaccionales. En el caso de operaciones individuales, se aprovechará la capacidad de Hibernate para realizar un rollback en caso de fallo o un commit 
si la operación es exitosa. Sin embargo, cuando se tratan de operaciones múltiples, Hibernate no gestiona transacciones compuestas, por lo que el framework se encargará de 
mantener la sesión activa y utilizar los métodos de Transaction, Commit o Rollback según sea necesario. El flujo natural será Transaction -> Operaciones CRUD -> Commit, todo 
dentro de una misma sesión. 
  
La arquitectura de la aplicación ha sido diseñada teniendo en cuenta la modularidad y la reutilización de componentes entre el cliente y el servidor. Se ha creado un ecosistema 
flexible que puede trabajar con distintos modelos de datos (Hibernate), aunque en el ejemplo de presentación, la interfaz de usuario solo gestionará el modelo predefinido. 
  
En términos de seguridad, el servidor implementará robustos mecanismos de autenticación mediante clave simétrica. Tanto el servidor como el cliente generarán esta clave durante 
el desarrollo inicial para su posterior uso. Cuando el cliente solicita una operación CRUD, los datos se cifran en un objeto que luego es enviado al servidor para su descifrado. 
Esto asegura la integridad de los datos durante la comunicación. 
  
La comunicación entre el cliente y el servidor se realizará a través del protocolo TCP mediante sockets, lo que garantizará que la sesión se mantenga activa durante las transacciones. 
En el lado del cliente, se desarrollará una clase que enmascarará las operaciones CRUD, simplificando significativamente el proceso de desarrollo de aplicaciones cliente. Esta clase 
proporcionará métodos directos y sencillos para interactuar con el servidor, permitiendo a los desarrolladores centrarse en la lógica de la aplicación en lugar de en los detalles de 
la comunicación. 
  
Además, el sistema incluirá una interfaz de escritorio para probar los aspectos básicos del framework en una base de datos de biblioteca, permitiendo el mantenimiento tanto de usuarios 
como de libros y la gestión de préstamos y devoluciones. Estas últimas operaciones aprovecharán específicamente el uso de transacciones. 
  
El servidor también contará con un sistema de gestión de errores que proporcionará información detallada sobre las operaciones realizadas, los errores ocurridos y otros eventos 
importantes. Esto será útil tanto durante la fase de desarrollo para depurar errores como en la fase de producción para monitorizar el comportamiento del servidor. 
  
La interfaz de usuario, aunque se gestionará en el cliente, será diseñada para ser intuitiva y responsiva, asegurando una experiencia de usuario fluida y eficiente. Se prestará 
especial atención al diseño de la interfaz para que sea accesible y fácil de usar, independientemente del nivel técnico del usuario. 
  
Asimismo, se considerará la internacionalización del servidor, permitiendo el uso del sistema en diferentes regiones con soporte para múltiples idiomas. Esto será esencial para 
facilitar la expansión y adaptabilidad del sistema en mercados internacionales. 
  
Por último, el desarrollo del servidor se llevará a cabo siguiendo metodologías ágiles, lo que permitirá un ciclo de desarrollo más rápido y adaptable. Esto será crucial para 
responder eficazmente a los cambios en los requisitos del proyecto y las necesidades del mercado. 
  
En conclusión, el desarrollo de este servidor representa un enfoque innovador y eficiente para la gestión de bases de datos. Al minimizar la necesidad de código repetitivo y maximizar 
la reutilización de componentes comunes, se espera lograr una mejora significativa en la productividad del desarrollo de aplicaciones. 
