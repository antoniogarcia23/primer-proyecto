package arraydinamico;

import java.util.Scanner;

/*
 * victor hora 9:50.
 */



/**
 * Codigo fuente para manipular una agenda de contactos 
 * Utilizando una array dinámico
 * @author profesor
 *
 */
public class Agenda
{
	/**
	 * Atributos.
	 */
	private int numContactos;
	private Contacto array[];	
	private Sting "antonio";
	
	/**
	 * Constructor
	 */
	public Agenda() 
	{
		numContactos = 0;
		array = new Contacto[numContactos];
	}
	public void prueba() {
		//prueba para git
	}
	
	/**
	 * unElementoMas es un método que pide memoria para un elemento más
	 * y copio en ese nuevo array los elementos que ya estaban en el 
	 * original. Para hacer esto no tenemos más remedio que utilizar una
	 * array auxiliar que apunte a la misma dirección que apunta array
	 */
	private void unElementoMas(Contacto aux[])
	{
		//Pedir memoria para un elemento más
		array = new Contacto[numContactos+1];
		
		//Copiamos los contactos anteriores en array
		for(int i = 0;i < numContactos; i++)
		{
			array[i] = aux[i];
		}
		
		//Actualizamo el numero de contactos
		numContactos++;
	}
	public void cambio3()
	{
		
	}
	public void leere()
	{
		
	}
	//////////////////////////////////////////////////////////
	/**
	 * Añade un elemento a la agenda
	 * @param contacto
	 */
	public void anadir(Contacto contacto)
	{
		unElementoMas(array);
		insertar(contacto, numContactos-1);		
	}
	/////////////////////////////////////////////////////////
	
	/**
	 * Inserta un contacto en la posición válida indicada
	 */
	public void insertar(Contacto contacto, int posicion)
	{
		//Validamos la posción
		if(posicion >= 0 && posicion < numContactos)
		{
			array[posicion] = contacto;
		}else
			System.out.println("La posición no es valida. ");
	}
	/**
	 * Este metodo pide memoria para uno menos y copia en el array 
	 * los elementos que no estén marcados 
	 */
	private void unElementoMenos(Contacto aux[])
	{
		//Pedimos memoria para un elemento menos
		array = new Contacto[numContactos-1];
		/**
		 * índice para posicionarme en el array destino.
		 */
		int  j = 0;
		
		//Copiamos en array todos aquellos menos el marcado con null
		for(int i = 0;i < numContactos; i++)
		{
			if(aux[i] != null)
			{
				array[j] = aux[i];
				j++;
			}
		}
		numContactos--;		
	}
	/////////////////////////
	/**
	 * Realiza una busqueda secuencial del contacto
	 * que le hemos pasado. Devuelve la posición en caso
	 * de que lo encuentre y -1 en caso contrario.
	 * @param contacto
	 * @return
	 */
	private int busqueda(Contacto contacto)
	{
		for(int i = 0; i < numContactos; i++)
		{
			if(array[i].equals(contacto))
				return i;
		}
		return -1;
	}
	/////////////////////////
	/**
	 * Recibe la posición donde está 
	 * el elemento 
	 * que quiero eliminar y lo marca asignándole un 
	 * null.
	 */
	private boolean marcar(int posicion)
	{
		array[posicion] = null;
		unElementoMenos(array);
		return true;
	}
	//////////////////////////////////////
	private int verMenu()
	{
		Scanner entrada = new Scanner(System.in);
		int opcion;
		boolean opcionValida;
		System.out.println("1. Nuevo Contacto");
		System.out.println("2. Consultar");
		System.out.println("3. Eliminar");
		System.out.println("4. Modificar");
		System.out.println("5. Ver todos los contactos");
		System.out.println("6. Salir");
		System.out.println("Introduce una opción: ");
		do
		{
			opcion = entrada.nextInt();
			opcionValida = (opcion >= 1 && opcion <= 6);
			if(!opcionValida)
				System.err.println("Error, introduce un número entre 1 y 5 para opción valida");
		}while(!opcionValida);
		
		return opcion;
	}
///////////////////////////////////////////////////////////////
public void arrancarAplicacion()
{
	boolean salir;

	do {
		int opcion = verMenu();
		salir = (opcion == 6);
		if(!salir)
		{
			switch(opcion)
			{
			case 1:
				nuevoContacto();
				break;

			case 2:
				consultas();
				break;

			case 3:
				eliminar();
				break;

			case 4:
				modificar();
				break;
				
			case 5:
				verTodosContactos();
				break;
			}
		}
			

	}while(!salir);
	System.out.println("Hasta pronto.");
	
	}

	////////////////////////////////
	/**
	 * Este metodo añade un nuevo contacto a la agenda
	 */
	private void nuevoContacto() {
		Contacto contacto = introducirDatos();
		
		// Comprobar si existe previamente
		int posicion = busqueda(contacto);
		boolean existe = (posicion != -1);
		if (existe) {
			System.out.println("Ese contacto ya existe");
		} else {
			anadir(contacto);
		}
		
	}
	
	/////////////////////////////////////////
	
	private void consultas() {
		// Comprobamos si la agenda está vacia
		if (agendaVacia()) {
			System.out.println("La agenda está vacia");
		} else {
				Contacto contacto = introducirDatos();
				// Comprobar si existe previamente
				int posicion = busqueda(contacto);
				boolean existe = (posicion != -1);
				if (existe) {
					//enviar a pantalla información de ese contacto.
					contacto.mostrarDatos();
				}else
				{
					System.out.println("El contacto no existe");
				}
		}
	}
	
	///////////////////////////////////////////
	
	private Contacto introducirDatos() {
		// Introducimos los datos
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Introduce el nombre");
		String nombre = entrada.nextLine();
		
		System.out.println("Introduce el telefono");
		String telefono = entrada.nextLine();
		
		// Instanciamos y devolvemos un nuevo contacto
		return new Contacto(nombre, telefono);
	}
	
	///////////////////////////////////////////
	
	private boolean agendaVacia() {
		return (numContactos == 0);
	}
	
	///////////////////////////////////////////
	private void eliminar()
	{
		// Comprobamos si la agenda está vacia
		if (agendaVacia())
		{
			System.out.println("La agenda está vacia");
		} 
		else {
				Contacto contacto = introducirDatos();
				// Comprobar si existe previamente
				int posicion = busqueda(contacto);
				boolean existe = (posicion != -1);
				if (existe)
				{
					//sí existe lo marcamos para eliminarlo.
					if(marcar(posicion))
					{
						System.out.println("El contacto ha sido eliminado");
					}
				}	
						else {
							System.out.println("El contacto no existe");
						}
			}
	}
	///////////////////////////////////////////
	private void modificar()
	{	
		Scanner entrada = new Scanner(System.in);
		// Comprobamos si la agenda está vacia
				if (agendaVacia())
				{
					System.out.println("La agenda está vacia");
				} 
				else {
						Contacto contacto = introducirDatos();
						// Comprobar si existe previamente
						int posicion = busqueda(contacto);
						boolean existe = (posicion != -1);
						if (existe) {
							System.out.println("Modifica los datos");
							System.out.println("Introduce el nuevo telefono");
							array[posicion].setTelefono(entrada.nextLine());
						}
						else {
							System.out.println("El contacto n oexiste");
						}
				}
	}
	///////////////////////////////////////////
	private void verTodosContactos()
	{
		if(agendaVacia()) {
			System.out.println("La agenda esta vacia");
		}
		else {
			for(int i = 0; i < numContactos; i++) {
				array[i].mostrarDatos();
			}
		}
	}
}


