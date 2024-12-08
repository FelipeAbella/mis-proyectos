/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Helados;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author abell
 */
public class Dibujadora extends javax.swing.JFrame {

    /**
     * Creates new form Pizarra
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setToolTipText("");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setToolTipText("");
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel2.setText("Make Ice Cream");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(44, 44, 44))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setToolTipText("");
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel4.setText("Recover Ice Cream");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(38, 38, 38))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setToolTipText("");
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel5.setText("Salir");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(148, 148, 148))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setToolTipText("");
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel3.setText("Clear Screen");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(139, 139, 139))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        this.dibujarHelado();
        this.gestionHelados();
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.jPanel1.printAll(this.pintor);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        this.recuperarHelados();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        this.salir();
    }//GEN-LAST:event_jPanel7MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables

    private Graphics pintor;
    private Circulo circulito;
    private Cilindro cilindrito;
    private Cono conito;
    private String ruta;
    
    public static void main(String args[]) {
     new Dibujadora();   
    }
    
    public Dibujadora() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pintor=this.jPanel1.getGraphics();
    }
    
    private void setColor()
    {
        int r,g,b;
        r=(int)(255*Math.random());
        g=(int)(255*Math.random());
        b=(int)(255*Math.random());
        Color colorcito=new Color(r,g,b);
        this.pintor.setColor(colorcito);
    }
    
    private void setColor2()
    {
        Color colorcito=new Color(255,181,82);
        this.pintor.setColor(colorcito);
    }
    
    private void setColor3()
    {
        Color colorcito=new Color(0,0,0);
        this.pintor.setColor(colorcito);
    }
    
    private void setColor4()
    {
        Color colorcito=new Color(255,255,255);
        this.pintor.setColor(colorcito);
    }
    
    private void setColor5()
    {
        Color colorcito=new Color(255,0,0);
        this.pintor.setColor(colorcito);
    }
    
    //CONSTRUIR HELADO
    public void dibujarHelado()
    {       
    int x,y,radio,altura;
    
    x=Integer.parseInt(JOptionPane.showInputDialog("Digite la coordenada x del helado: "));
    y=Integer.parseInt(JOptionPane.showInputDialog("Digite la coordenada y del helado: "));
    radio=Integer.parseInt(JOptionPane.showInputDialog("Digite el radio del helado: "));
    altura=Integer.parseInt(JOptionPane.showInputDialog("Digite la altura del helado: "));
    
    this.circulito=new Circulo (x,y,radio);
    this.cilindrito=new Cilindro(x,y,radio,0);
    this.conito=new Cono(x,y,radio,altura);
    
    //DIBUJAR PITILLO
    
    Color colorcito=new Color(0,0,0);
    this.pintor.setColor(colorcito);
        
        
    this.pintor.drawLine(this.conito.getX()-(this.conito.getDim1()/5), this.conito.getY(), this.conito.getX()+(this.conito.getDim1()/4), this.conito.getY());
    this.pintor.drawLine(this.conito.getX()-(this.conito.getDim1()/5), this.conito.getY(), this.conito.getX()-(this.conito.getDim1()/4), this.conito.getY()-this.conito.getDim2());
    this.pintor.drawLine(this.conito.getX()+(this.conito.getDim1()/5), this.conito.getY(), this.conito.getX()+(this.conito.getDim1()/4), this.conito.getY()-this.conito.getDim2());
    this.pintor.drawLine(this.conito.getX()-(this.conito.getDim1()/5), this.conito.getY()-this.conito.getDim2(), this.conito.getX()+(this.conito.getDim1()/4), this.conito.getY()-this.conito.getDim2());
    
    //DIBUJAR CEREZA
    this.setColor5();
    this.pintor.fillOval(this.circulito.getX()-this.circulito.getDim1(), this.circulito.getY()-this.circulito.getDim1(), this.circulito.getDim1()/2, this.circulito.getDim1()/2);
    
    //DIBUJAR BOLA 
    
    this.setColor();
    this.pintor.fillOval(this.circulito.getX()-this.circulito.getDim1(), this.circulito.getY()-this.circulito.getDim1(), 2*this.circulito.getDim1(), (int) (1.6*this.circulito.getDim1()));
    
    //DIBUJAR CONO

    this.setColor3(); 
    this.pintor.drawLine(this.conito.getX()-this.conito.getDim1(), this.conito.getY(), this.conito.getX(), this.conito.getY()+this.conito.getDim2());
    this.pintor.drawLine(this.conito.getX()+this.conito.getDim1(), this.conito.getY(), this.conito.getX(), this.conito.getY()+this.conito.getDim2());
    
    this.setColor4();
    this.pintor.drawLine(this.conito.getX()-this.conito.getDim1(), this.conito.getY(), this.conito.getX()+this.conito.getDim1()+5, this.conito.getY());
    
    this.setColor2();
    int[] puntosX={this.conito.getX()-this.conito.getDim1(),this.conito.getX()+this.conito.getDim1(),this.conito.getX()};
    int[] puntosY={this.conito.getY(),this.conito.getY(),this.conito.getY()+this.conito.getDim2()};
    this.pintor.fillPolygon(puntosX, puntosY, 3);
    
    //DIBUJAR CILINDRO
    int respuesta;
    
    this.setColor();
    this.pintor.fillOval(this.cilindrito.getX()-this.cilindrito.getDim1(), this.cilindrito.getY()-this.cilindrito.getDim1()+5*this.cilindrito.getDim1()/6,2*this.cilindrito.getDim1(),this.cilindrito.getDim1()/3);
        
    respuesta=Integer.parseInt(JOptionPane.showInputDialog("¿Desea guardar su helado? (1=Sí, 2=No)"));
        if(respuesta==1)
        {
            //construir objeto tipo cono
            Cono heladito=new Cono(x, y, radio, altura);
       
            this.guardarHelado(heladito);
            JOptionPane.showMessageDialog(null, "Operación exitosa");
        }
    }
        
    //GUARDAR HELADO
    
    public void gestionHelados()
    {
        this.ruta="./Archivos/misHelados.txt";
        this.verificArchivo();
    }
    public void verificArchivo()
    {
        try
        {
            File filex = new File(this.ruta);
            if(!filex.exists())
                filex.createNewFile();  
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Errores encontrando ruta");
        }
    }
    
    public void guardarHelado(Cono heladito)
   {
       File file=new File(ruta);
       try
       {
           FileWriter fr = new FileWriter(file,true);
           PrintWriter ps = new PrintWriter(fr);
           ps.println(heladito);
           ps.close();
       }
       catch (IOException ex) 
       {
           JOptionPane.showMessageDialog(null, "Fallo guardando el helado");
       }
   }
    
    //RECUPERAR HELADO
    public Cono recuperarHelados()
   {
       Cono heladito=null;
       FileReader file;
       String registro;
       
       try
       {
           file=new FileReader(ruta);
           Scanner lector = new Scanner(file);
           while(lector.hasNextLine())
           {
               registro=lector.nextLine();
               String [] campos=registro.split(",");
               if(campos[0]!=null)
               {
//                   heladito=new Cono(campos[0],campos[1],campos[2],campos[3]);
                   System.out.println("Los helados han sido encontrados");
                   break;
               }
           }
       }
       catch (FileNotFoundException ex)
       {
           JOptionPane.showMessageDialog(null, "Fallo leyendo el archivo");
       }
       return heladito;
   }
    
    private void salir()
    {
        System.exit(0);
    }
}