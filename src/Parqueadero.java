import javax.swing.JOptionPane;

public class Parqueadero {
    public static void main(String[] args) {
        
        int opcion = 0;
        int puestos = 10;
        int[][] parqueadero = new int[puestos][3];

            for(int i = 0; i < puestos; i++){
                parqueadero[i][0] = i;
                parqueadero[i][1] = 0;
            }

            do {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("=== PARQUEADERO ===\n" + "1. Registrar entrada\n"
                + "2. Registrar salida\n" + "3. Ver puestos\n" + "4. Salir"));

                switch(opcion) {
                    case 1:
                        int horaEntrada;
                        boolean disponible = false;

                        try {
                            horaEntrada = Integer.parseInt(
                                JOptionPane.showInputDialog("Ingrese la hora de entrada (0-23)"));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros");

                            break;
                        }
                        if (horaEntrada < 0 || horaEntrada > 23) {
                            JOptionPane.showMessageDialog(null, "Hora invalida");
                            break;
                        }
                        for (int i = 0; i < puestos; i++) {
                            if (parqueadero[i][1] == 0) {
                                parqueadero[i][1] = 1;
                                parqueadero[i][2] = horaEntrada;

                                disponible = true;

                                JOptionPane.showMessageDialog(null, "Vehiculo registrado en el puesto " + i);

                                break;
                            }
                        }
                        if (!disponible) {
                            JOptionPane.showMessageDialog(null, "No hay puestos disponibles");
                        }
                    break;

                    case 2:
                        //Registrar la salida

                    break;

                    case 3:
                        String estado = "";
                        for (int i = 0; i < puestos; i++) {
                            if (parqueadero[i][1] == 0) {
                                estado += "Puesto " + i + " - Libre\n";
                            } else {
                                estado += "Puesto " + i + " - Ocupado\n";
                            }
                    }    
                        JOptionPane.showMessageDialog(null, estado);
                    break;

                    case 4:
                        //salir del parqueadero
                    break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } while (opcion != 4);

        JOptionPane.showMessageDialog(null, "ya funciona?");
    }

}