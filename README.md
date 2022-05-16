# FAKEPIGEONS
## PRACTICA FINAL PROGRAMACIÓN DE APLICACIONES TELEMÁTICAS.
## CREADORES
- Alejandro Manuel López Gómez (Sistema de Mensajeria)
- Gonzalo José Barceló (Sistema de Usuarios)
- María Costa (Diseño de HTML y CSS)

## DESCRICIÓN GENERAL
fakepigeons es una aplicación de chat que incorpora funcionalidades de mensajería privada y un chat global para todos los usuarios. Ha sido creada empleando componentes propios de SpringBoot dedicados especificamente a la creación de aplicaciones de chat.

Se ha puesto énfasis en la seguridad de nuestros usuarios, empleando Spring Security y procesos de encritación extra. El usuario podrá elegir entrar en el chat global o en su espacio personal. El chat global no requiere de autenticación, incluso usuarios no identificados pueden sumarse a la conversación.

## SISTEMA DE MENSAJERÍA (Alejandro)
Como paradigma de programación, se emplea programación reactiva con una arquitectura Event-Driven. Empleando Brokers de Mensajeria de Springboot (en este caso STOMP), se permite la creación de puntos de acceso o WebSockets a los cuales el controller de la aplicación ofrece acceso.

El funcionamiento genérico es el siguiente. Se crea un endpoint al cual se suscriben los usuarios. Al recibirse un mensaje en el puerto de escucha abierto se ejecutan automáticamente las funciones encargadas de mostrar este mensaje. Al enviar un mensaje al endpoint también se guarda en una base de datos para que teóricamente al reiniciar la aplicación se carguen los mensajes anteriores. El modelo seguido es resumible en la siguiente imagen.

<img src="https://www.altexsoft.com/media/2021/06/key-components-of-event-driven-architectures.png" width="50%" height="50%">
