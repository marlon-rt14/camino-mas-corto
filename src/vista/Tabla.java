package vista;

import java.awt.Dimension;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JPanel {

    DefaultTableModel modelo;
    int cantidad = 0;
    String header[];

    public Tabla(int cant, DefaultTableModel dtm) {

        cantidad = cant + 1;
        modelo = dtm;
        char[] columnas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        header = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            header[i] = "";
        }

        int cont = 1;
        int tam = 1;
        boolean unaVez = true;
        while (cont < cantidad) {
            for (int i = 0; i < columnas.length && cont < cantidad; i++) {
                if (cantidad > columnas.length) {
                    if (unaVez) {
                        for (int j = 0; j < columnas.length && cont < cantidad; j++) {
                            header[cont] = header[cont] + String.valueOf(columnas[j]);
                            cont++;
                        }
                        i--;
                        unaVez = false;
                    } else {
                        header[cont] = header[tam] + String.valueOf(columnas[i]);
                        cont++;
                    }
                } else {
                    header[cont] = header[cont] + String.valueOf(columnas[i]);
                    cont++;
                }
            }
            tam++;
        }

        modelo = new DefaultTableModel(header, 0);
//        JTable tabla = new JTable(dtm);
//        
//        tabla.setPreferredScrollableViewportSize(new Dimension(450,103));
//        tabla.setFillsViewportHeight(true);
//        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        
//        JScrollPane js = new JScrollPane(tabla);
//        js.setVisible(true);
//        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        add(js);
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
    
    public String[] getColumnas(){
        return header;
    }
}
