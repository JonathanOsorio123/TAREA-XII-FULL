Reporte de Laboratorio: Optimización en Procesamiento Masivo de Datos
Descripción General
Este proyecto implementa una solución de software orientada al procesamiento masivo de datos en Java. El sistema aborda la problemática común de la saturación de memoria RAM al manipular millones de registros de clientes en sistemas empresariales de telecomunicaciones.
Evolución del Sistema y Estrategia de Optimización
•	Enfoque Tradicional: Originalmente, el sistema cargaba la totalidad de los datos estructurados en listas dinámicas dentro de la memoria, aplicando búsquedas lineales para clasificar y agrupar campañas. Esto resultaba en un uso ineficiente de recursos y en la degradación del rendimiento temporal a medida que crecía el volumen de datos.
•	Enfoque Optimizado: Se sustituyó la arquitectura de carga masiva por un modelo de procesamiento secuencial por flujo de datos (Stream Parsing), el cual lee el archivo físico línea por línea. Las búsquedas y agrupaciones complejas se optimizaron mediante tablas de dispersión (HashMap), reduciendo la complejidad temporal de inserción y búsqueda a tiempo constante promedio.
Atributos de la Entidad (cliente)
Cada registro modelado dentro del flujo masivo de datos consta de las siguientes propiedades operacionales:
•	id: Identificador único secuencial del registro.
•	nombre: Rótulo de identificación del cliente.
•	ingreso: Capacidad económica mensual calculada.
•	segmento: Clasificación comercial (PREPAGO, POSTPAGO, RESIDENCIAL, etc.).
•	region: Sector geográfico de operaciones (NORTE, CENTRO, SUR, etc.).
•	score: Calificación paramétrica de riesgo crediticio (0 - 1000).
•	deuda: Saldo pendiente en la cuenta del usuario.
•	jsonData: Cadena de texto de alta densidad (payload JSON simulado) utilizada para estresar la transferencia de lectura y escritura.
Reglas de Negocio y Funcionamiento Técnico
1.	Persistencia y Simulación: La clase generadora produce de forma asíncrona un archivo plano de extensión .csv con un volumen configurable (hasta 2,000,000 de instancias de prueba).
2.	Lectura por Flujo (Streaming): Mediante la implementación de la clase BufferedReader, el procesador lee de forma síncrona el archivo línea por línea desde el almacenamiento persistente, evitando la retención masiva de objetos en la memoria interna de la Java Virtual Machine.
3.	Clasificación Paramétrica y Mapeo: El motor calcula una clave compuesta única derivada de los atributos del cliente (Segmento + Región + Nivel de Ingreso + Score + Deuda). Esta clave determina el identificador de la campaña y se consolida dinámicamente en el mapa usando la función de dispersión.
Métricas de Rendimiento Evaluadas
El sistema expone de manera explícita en consola indicadores clave de rendimiento para la auditoría del software:
•	Tiempo de instanciación y escritura del archivo origen en milisegundos.
•	Tiempo de lectura, análisis gramatical y mapeo de campañas en milisegundos.
•	Uso analítico de memoria de la Máquina Virtual de Java dividida en: Memoria Usada, Memoria Total Asignada y Memoria Máxima Permitida.
Arquitectura del Repositorio y Código Fuente
El código se diseñó siguiendo principios de cohesión alta y acoplamiento débil, estructurándose de la siguiente forma:
tarea-procesamiento-masivo-java/ ├── src/ │ └── com/ │ └── mycompany/ │ └── tarea/ │ ├── Tarea.java
│ ├── cliente.java
│ ├── GeneradorCliente.java
│ └── ProcesadorOptimizado.java
│ ├── evidencia/ │ └── capturas.png
└── README.md
Instrucciones para Despliegue y Ejecución Local
Para compilar y ejecutar de forma nativa a través de la terminal de comandos del sistema operativo:
Fase de Compilación: javac src/com/mycompany/tarea/*.java -d target/classes
Fase de Ejecución: java -cp target/classes com.mycompany.tarea.Tarea
Conclusiones del Laboratorio
1.	Sustentabilidad de la Memoria: La ejecución del programa demostró que procesar colecciones de gran escala (2 millones de registros) mediante flujos secuenciales mantiene el uso de memoria RAM bajo un perfil controlado y constante, independientemente del tamaño final del set de datos.
2.	Eficiencia Algorítmica: El uso de estructuras clave-valor (HashMap) minimiza exponencialmente el costo computacional de clasificación, transformando procesos que originalmente requerían búsquedas repetitivas en ejecuciones directas de alta velocidad.
Información del Desarrollador
•	Nombre Completo: Jonathan Juan David Osorio Pascual
•	Carnet Universitario: 9941-24-14047
•	Institución: Universidad Mariano Gálvez de Guatemala
