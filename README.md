
**Trabajo-IHC**
===========
Grupo 2
-------

Repositorio para el trabajo realizado en el curso IHC Primer Semestre 2014.
USACH.


----------

GitHub y GIT.
--------------------------

 - GIT: 

>Git es un software para el control de versiones desarrollado en un principio por Linus Torvalds. Permite trabajar en repositorios locales y/o repositorios remotos.


 - GitHub: 

>Web que nos permite almacenar gran cantidad de repositorios (de codigo abierto en la versión gratuita) y además permite la interacción entre usuarios y repositorios (una especie de red social de código).


 


----------


Tutorial uso Git
----------

### Inicializar repositorio local: 

Para obtener las fuentes se debe clonar el repositorio remoto, es decir, debemos obtener las fuentes de la web de GitHub. Para esto se debe ejecutar el siguiente código en la consola:

`git clone https://github.com/IHC2014/Trabajo-IHC.git`


### Subir Fuentes a repositorio remoto: 

Para subir las fuentes a un repositorio remoto se deben seguir principalmente 3 pasos: 

  - ***Agregar Fuentes a la zona de trabajo***
  
    Ante de subir los cambios al repositorio local se deben añadir los cambios generados en el código, para esto deben ejecutar el siguiente código:
    `git add -A`

  - ***Agregar Fuentes al repositorio local***

    Luego de añadir los cambios podemos subir el código al repositorio local, para realizar esta tarea se debe ejecutar el siguiente código:
    `git commit -m "Mensaje"`
    
    Esto no permite agregar una pequeña descripción de los cambios que se agregaron al repositorio local.

  - ***Agregar Fuentes al repositorio remoto***

  
    Finalmente si se necesita subir el código a un servidor remoto, luego de realizar los pasos anteriores, se debe cd ejecutar el siguiente comando:
    `git push`
    
    En este paso Git debería solicitar las credenciales necesarias para poder realizar los cambios en el servidor.
    
    
Herramientas GitHub
----------

Para los que trabajan en Windows GitHub entrega una herramienta que facilita el trabajo con la consola, pueden descargar la aplicación en el siguiente [link](https://windows.github.com/).


Links de interes
----------

- [Página oficial Git](http://git-scm.com/)
- [Manuales Git](http://lmgtfy.com/?q=manual+git#)
