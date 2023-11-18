import java.time.LocalDate;


/**
 * La interfaz UsuarioBase proporciona métodos básicos para operaciones de usuario.
 */
interface UsuarioBase {
    /**
     * Cambia el tipo de cliente del usuario.
     *
     * @param nuevoTipo El nuevo tipo de cliente.
     */
    public void cambiarTipoCliente(String nuevoTipo);
    /**
     * Aplica un descuento de cupón al usuario.
     */
    public void aplicarDescuentoCupon();
    /**
     * Cambia la contraseña del usuario.
     *
     * @param nuevoContraseña La nueva contraseña.
     */
    public void cambiarContraseña(String nuevoContraseña);
    /**
     * Cambia la contraseña del usuario.
     *
     * @param nuevoContraseña La nueva contraseña.
     */
    public void hacerReserva(LocalDate fecha, String tipoViaje, int cantidadBoletos, String aerolinea);
    /**
     * Realiza la confirmación de la reserva actual del usuario.
     *
     * @param numeroTarjeta El número de tarjeta para el pago.
     * @param cuotas        La cantidad de cuotas para el pago.
     * @param claseVuelo    La clase de vuelo seleccionada.
     */
    public void hacerConfirmacion(String numeroTarjeta, int cuotas, String claseVuelo);
      /**
     * Imprime el itinerario de la reserva actual del usuario.
     */
    public void imprimirItinerario();
}