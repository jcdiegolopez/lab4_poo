import java.time.LocalDate;

/**
 * La clase Reserva representa la información de una reserva de vuelo.
 */
class Reserva {
    private int idUsuario;
    private LocalDate fechaViaje;
    private String tipoVuelo;
    private int cantidadBoletos;
    private String aerolinea;
    private boolean confirmado;
    private String numeroTarjeta;
    private int cantidadCuotas;
    private String claseVuelo;

    /**
     * Constructor para crear una nueva instancia de la clase Reserva.
     *
     * @param idUsuario      El ID del usuario que realiza la reserva.
     * @param fechaViaje     La fecha del viaje.
     * @param tipoVuelo      El tipo de vuelo (Coach o Primera Clase).
     * @param cantidadBoletos La cantidad de boletos reservados.
     * @param aerolinea      La aerolínea seleccionada.
     */
    public Reserva(int idUsuario, LocalDate fechaViaje, String tipoVuelo, int cantidadBoletos, String aerolinea) {
        this.idUsuario = idUsuario;
        this.fechaViaje = fechaViaje;
        this.tipoVuelo = tipoVuelo;
        this.cantidadBoletos = cantidadBoletos;
        this.aerolinea = aerolinea;
        this.confirmado = false;
    }

    /**
     * Confirma la reserva con la información de pago y la clase de vuelo.
     *
     * @param numeroTarjeta El número de tarjeta para el pago.
     * @param cantidadCuotas La cantidad de cuotas para el pago.
     * @param claseVuelo La clase de vuelo seleccionada.
     */
    public void confirmarReserva(String numeroTarjeta, int cantidadCuotas, String claseVuelo) {
        this.confirmado = true;
        this.numeroTarjeta = numeroTarjeta;
        this.cantidadCuotas = cantidadCuotas;
        this.claseVuelo = claseVuelo;
    }
    /**
     * Muestra el itinerario de la reserva, incluyendo la información de la reserva
     * y la confirmación.
     *
     * @param usuario El usuario asociado a la reserva.
     * @return Una cadena con el itinerario completo.
     */
    public String mostrarItinerario(Usuario usuario) {
        return obtenerInformacionReserva(usuario) + obtenerInformacionConfirmacion(usuario);
    }
    /**
     * Obtiene la información de la reserva.
     *
     * @param usuario El usuario asociado a la reserva.
     * @return Una cadena con la información de la reserva.
     */
    public String obtenerInformacionReserva(Usuario usuario) {
        return "Información de la reserva:\n" +
               "Fechas de vuelo: " + this.fechaViaje + "\n" +
               "Tipo de vuelo: " + this.tipoVuelo + "\n" +
               "Cantidad de boletos: " + this.cantidadBoletos + "\n" +
               "Aerolínea: " + this.aerolinea + "\n" +
               "Nombre del usuario: " + usuario.getNombre() + "\n";
    }
    /**
     * Obtiene la información de confirmación de la reserva.
     *
     * @param usuario El usuario asociado a la reserva.
     * @return Una cadena con la información de confirmación.
     */
    public String obtenerInformacionConfirmacion(Usuario usuario) {
        return "Información de confirmación:\n" +
               "Información de pago: Tarjeta terminada en " + this.numeroTarjeta.substring(this.numeroTarjeta.length() - 4) + "\n" +
               "Clase del vuelo: " + this.claseVuelo + "\n" +
               "Cantidad de maletas: " + (usuario.getTipo().equals("PREMIUM") ? 2 : 1) + "\n";
                // + "Cupones aplicados: " + cupones + "\n";
    }
    
    public boolean getConfirmado(){
        return this.confirmado;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public LocalDate getFechaViaje() {
        return this.fechaViaje;
    }

    public String getTipoVuelo() {
        return this.tipoVuelo;
    }

    public int getCantidadBoletos() {
        return this.cantidadBoletos;
    }

    public String getAerolinea() {
        return this.aerolinea;
    }

    public boolean isConfirmado() {
        return this.confirmado;
    }

    public String getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    public int getCantidadCuotas(){
        return this.cantidadCuotas;
    }

    public String getClaseVuelo(){
        return this.claseVuelo;
    }

    public void setConfirmado(boolean confirmado){
        this.confirmado = confirmado;
    }


    public void  setNumeroTarjeta(String num){
        this.numeroTarjeta = num;
    }

    public void  setCuotas(int cuotas){
        this.cantidadCuotas = cuotas;
    }

    public void setClaseVuelo(String claseVuelo){
        this.claseVuelo = claseVuelo;
    }

}