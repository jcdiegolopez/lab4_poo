import java.time.LocalDate;

/**
 * La clase Usuario representa a un usuario del sistema de reservas de vuelos.
 */
class Usuario implements UsuarioBase {
    private int idUsuario;
    private String nombreUsuario;
    private String contraseña;
    private String tipoCliente;
    private Reserva reservaActual;


    /**
     * Constructor para crear una nueva instancia de la clase Usuario.
     *
     * @param idUsuario     El ID del usuario.
     * @param nombreUsuario El nombre de usuario.
     * @param contraseña    La contraseña del usuario.
     * @param tipoCliente   El tipo de cliente (por ejemplo, "PREMIUM").
     */
    public Usuario(int idUsuario, String nombreUsuario, String contraseña, String tipoCliente) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipoCliente = tipoCliente;
    }
    /**
     * Cambia el tipo de cliente del usuario.
     *
     * @param nuevoTipo El nuevo tipo de cliente.
     */
    public void cambiarTipoCliente(String nuevoTipo) {
        this.tipoCliente = nuevoTipo;
    }

    /**
     * Aplica un descuento de cupón al usuario.
     */
    public void aplicarDescuentoCupon() {
        System.out.println("Cupon aplicado");
    }

    /**
     * Cambia la contraseña del usuario.
     *
     * @param nuevoContraseña La nueva contraseña.
     */
    public void cambiarContraseña(String nuevoContraseña) {
        this.contraseña = nuevoContraseña;
    }

    /**
     * Realiza una nueva reserva para el usuario.
     *
     * @param fecha          La fecha del viaje.
     * @param tipoViaje      El tipo de viaje (Coach o Primera Clase).
     * @param cantidadBoletos La cantidad de boletos a reservar.
     * @param aerolinea      La aerolínea seleccionada.
     */
    public void hacerReserva(LocalDate fecha, String tipoViaje, int cantidadBoletos, String aerolinea) {
        this.reservaActual = new Reserva(this.idUsuario, fecha, tipoViaje, cantidadBoletos, aerolinea);
    }

    /**
     * Realiza la confirmación de la reserva actual del usuario.
     *
     * @param numeroTarjeta El número de tarjeta para el pago.
     * @param cuotas        La cantidad de cuotas para el pago.
     * @param claseVuelo    La clase de vuelo seleccionada.
     */
    public void hacerConfirmacion(String numeroTarjeta, int cuotas, String claseVuelo) {
        this.reservaActual.confirmarReserva(numeroTarjeta, cuotas, claseVuelo);
    }

    /**
     * Imprime el itinerario de la reserva actual del usuario.
     */
    public void imprimirItinerario() {
        System.out.println(this.reservaActual.mostrarItinerario(this));
    }

    /**
     * Obtiene la información de la reserva actual del usuario.
     *
     * @return Una cadena con la información de la reserva actual.
     */
    public String obtenerInformacionReserva() {
        return (reservaActual != null) ?
               reservaActual.obtenerInformacionReserva(this) :
               "No hay reserva actual.";
    }
    
    /**
     * Obtiene la información de confirmación de la reserva actual del usuario.
     *
     * @return Una cadena con la información de confirmación de la reserva actual.
     */
    public String obtenerInformacionConfirmacion() {
        return (reservaActual != null && reservaActual.isConfirmado()) ?
               reservaActual.obtenerInformacionConfirmacion(this) :
               "No hay confirmación de reserva.";
    }
    

    public int getUsuarioId() {
        return this.idUsuario;
    }

    public String getNombre() {
        return this.nombreUsuario;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public Reserva getReserva() {
        return this.reservaActual;
    }

    public void setReserva(Reserva reserva) {
        this.reservaActual = reserva;
    }

    public String getTipo() {
        return this.tipoCliente;
    }
}