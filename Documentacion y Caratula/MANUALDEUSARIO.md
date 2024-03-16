# <center> MANUAL DE USUARIO </center>

El siguiente proyecto es llamado **Arbol binario de Ecuaciones** el cual representan el código de nivel del lenguaje en forma de datos. Los datos se almacenan en una estructura con forma de árbol. Cada nodo del árbol de expresión representa una expresión, por ejemplo, una llamada al método o una operación binaria, como x < y.

A continuación estaremos visualizando los respectivos recorridos **Postorden, Polaca y Postfija** el cual el usario podrá ingresar ciertos números para armar una expresión mátematica donde lo ordera y se visualizará un árbol gráfcio. 

## Creación de la Clase Nodo
<center> ATRIBUTOS </center>

Almacenamos nuestros valor operardor en **char** para que podamos oeprar nuestra expresión mátematica (+,-,*,/). 

<center> CONSTRUCTORES </center>

```JAVA 



    public Nodo(char operador) {
	        this.operador = operador;
	        this.valor = -1; // Valor predeterminado para operadores
	        this.izquierdo = this.derecho = null;
	    }


	    public Nodo(int valor) {
	        this.operador = ' ';
	        this.valor = valor;
	        this.izquierdo = this.derecho = null;
	    }

```
Asignamos valores iniciales a sus variables. 

## Creación de la Clase Arbol

Llamamos a nuestra clase Nodo y empezamos a realizar los recorridos el cual el paso a paso de **POSTORDEN** es: 

* Izquierda
* Derecha
* Raíz 

Utilizando condicionales para poder evuluar si el número es menor dirigase hacia la izquierda y si es mayor dirijase a la derecha etc. El recorrido lo realiza se dirige hasta la última hoja izquierda luego evalúa si tenemos una hacia la derecha y luego evalúa raíz. 

 ```JAVA
 private void recorrerPostorden(Nodo nodo) {
	        if (nodo == null) {
	            return;
	        }

	        // Recorrer el subarbol izquierdo
	        recorrerPostorden(nodo.izquierdo);

	        // Recorrer el subarbol derecho
	        recorrerPostorden(nodo.derecho);

	        // Mostrar el nodo (operando u operador)
	        if (nodo.operador == ' ') {
	            System.out.print(nodo.valor + " ");
	        } else {
	            System.out.print(nodo.operador + " ");
	        }
	    }

```

Ejemplo: 

<image src="https://www.google.com/url?sa=i&url=https%3A%2F%2Fjfernandeze.wordpress.com%2F2013%2F10%2F03%2Farboles-en-c-arboles-n-arios-con-entityframework%2F&psig=AOvVaw3hwZoTkWcILUKinVPfYLjn&ust=1710639332579000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCKDD-6XS94QDFQAAAAAdAAAAABAD" alt="ARBOL POSTORDEN">

Como segundo paso hacemos el recorrido en **POLACA** el recorrido de este es:

* Raíz 
* Izquierda
* Derecha

Utilizando condicionales para poder evuluar si el número si el número es menor dirigase hacia la izquierda y si es mayor dirijase a la derecha etc. El recorrido lo realiza evaluando la ríz se dirige a su hijo izquiero y luego verifica si tiene un hijo derecho.

```JAVA

 private void recorrerPolaca(Nodo nodo) {
	        if (nodo == null) {
	            return;
	        }

	        // Mostrar el nodo (operando u operador)
	        if (nodo.operador == ' ') {
	            System.out.print(nodo.valor + " ");
	        } else {
	            System.out.print(nodo.operador + " ");
	        }

	        // Recorrer el subarbol izquierdo
	        recorrerPolaca(nodo.izquierdo);

	        // Recorrer el subarbol derecho
	        recorrerPolaca(nodo.derecho);
	    }

```
Ejemplo:

<image src="https://www.oscarblancarteblog.com/wp-content/uploads/2014/08/preorden.png" alt="ARBOL POLACA">

## CREACIÓN DE LA CLASE ARBOL VISUAL

Para poder visualizar el árbol grafico utilizamos los siguientes paquetes:
  
* import javax.swing.*;
* import java.awt.*;
* import java.util.HashMap;
* import java.util.Map;
* import java.util.Scanner;

## JAVA.SWING

Incluye widgets para interfaz gráfica de usuario tales como cajas de texto, botones, listas desplegables y tablas

## JAVA.AWT

Se utiliza para crear interfaces gráficas de usuario (GUI) en aplicaciones Java

## JAVA.HASMAP

Se utiliza para almacenar datos en forma de pares clave-valor. Cada elemento en un HashMap es un par de valores: una clave única y su correspondiente valor asociado.

## JVA.UTIL.MAP

Es una interfaz en Java que representa una colección de pares clave-valor, donde cada clave debe ser única y está asociada con un único valor.

#

Utilizando estos paquetos nos ayudaron a diseñar una interfaz gráfica para que el usuario pueda visualizar el recorrido del árbol.

En el menú principal solicitamos los datos de la expresión matemática ingresados por el usuario, para poder almacenar los datos en las variables.

Así mismo el programa empezará a evualuar los datos atravez de un ciclo WHILE el cual recorrera cada dato para poder armar el árbol dependiendo del tamaño asignado. 



