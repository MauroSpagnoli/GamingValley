#include <iostream>

struct Nodo
{
    int dato;
    Nodo *izq;
    Nodo *der;
};

struct Estructura
{
    Nodo *inicio;
    Nodo *fin;
};

void inicializarEstructuraNoDinamica (Estructura &est)
//PRECONDICION: est.inicio es nullptr y est.fin es nullptr
/* Cuando sales de la función se van a eliminar los tres nodos, porque son variables locales.
 * Esto es muy importante. Los únicos nodos que permanecen en memoria cuando sales de una función son aquellos que
 * se reservan dinámicamente. */
{
    //Creacion de los nodos
    struct Nodo nodoIzq;
    struct Nodo nodoCentro;
    struct Nodo nodoDcha;
    //Introduccion datos en los nodos
    nodoIzq.dato = 2;
    nodoCentro.dato = 1;
    nodoCentro.dato = 3;
    //Poner los datos a nullptr
    nodoIzq.izq = nullptr;
    nodoCentro.der = nullptr;
    nodoDcha.der = nullptr;
    //Unimos los punteros
    est.inicio=nodoIzq.der;
    est.fin=nodoDcha.izq;
    nodoIzq.der=nodoDcha.izq;
    nodoDcha.izq=nodoCentro.der;
    nodoCentro.izq=nodoIzq.der;
}

void inicializarEstructuraDinamica (Estructura &est)
//PRECONDICION: est.inicio es nullptr y est.fin es nullptr
{
    //Creacion de los nodos
    Nodo *nodoIzq;
    Nodo *nodoCentro;
    Nodo *nodoDcha;
    //Inicializacion de los nodos dinamicamente
    nodoIzq = new Nodo;
    nodoCentro = new Nodo;
    nodoDcha = new Nodo;
    //Introduccion datos en los nodos
    //Tambien valdria: (*nodoIzq).dato = 2
    nodoIzq->dato = 2;
    nodoCentro->dato = 1;
    nodoDcha->dato = 3;
    //Poner los datos a nullptr
    nodoIzq->izq = nullptr;
    nodoCentro->der = nullptr;
    nodoDcha->der = nullptr;
    //Unimos los punteros
    est.inicio=nodoIzq;
    est.fin=nodoDcha;
    nodoIzq->der=nodoDcha; //Si nodoDcha->izq apuntara al nodoAbajo, nodoDcha->izq apuntaria a nodoAbajo y no a donde te dicen
    nodoDcha->izq=nodoCentro;
    nodoCentro->izq=nodoIzq;
}


int main()
{
    struct Estructura miEstructura; //El struct no es necesario de poner en C++
    miEstructura.inicio = nullptr;
    miEstructura.fin = nullptr;
    inicializarEstructuraDinamica(miEstructura);
    std::cout<<"Dato: "<<miEstructura.fin->izq->dato<<std::endl; //Imprimiria el 1
    //miEstructura.fin apuntaria a nodoIzq
    //nodoIzq->izq apuntaria a nodoCentro
    //nodoCentro->dato es igual a 1
    return 0;
}
