import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
  JLabel msg;
  TiposPrimitivos tipo;
  Ponto2D p1;
  Ponto2D p2;
  Reta2D rOld;

  public PainelDesenho(JLabel msg, TiposPrimitivos tipo) {
    this.tipo = tipo;
    this.msg = msg;
    addMouseListener(this);
    addMouseMotionListener(this);
  }
  
  public void setTipo(TiposPrimitivos tipo) {
    this.tipo = tipo;
    if (tipo == TiposPrimitivos.RETAS) {
      msg.setText("Primitivo: RETA ");
    }
    else if (tipo == TiposPrimitivos.CIRCULOS) {
      msg.setText("Primitivo: CIRCULO ");
    }
    else {
      msg.setText("Primitivo: NENHUM ");
    }
  }
  
  public TiposPrimitivos getTipo() {
    return tipo;
  }
  

  public void paintComponent(Graphics g) {
    desenharPrimitivos(g);
  }

  public void mousePressed(MouseEvent e) {
    if (e.getButton() == 1) {
      p1 = new Ponto2D(e.getX(), e.getY());
      //p2 = p1;
    }
  }

  public void mouseReleased(MouseEvent e) {

  }

  public void mouseMoved(MouseEvent e) {
      message(e);
  }

  public void mouseClicked(MouseEvent e) {}

  public void mouseEntered(MouseEvent e) {}

  public void mouseExited(MouseEvent e) {}
  

  public void mouseDragged(MouseEvent e) {

      message(e);
      p2 = new Ponto2D(e.getX(), e.getY());
      repaint();
  }

  public void message(MouseEvent e){
      if (tipo == TiposPrimitivos.RETAS) {
          msg.setText("Primitivo: RETA (" + e.getX() + ", " + e.getY() + ")");
      } else if (tipo == TiposPrimitivos.CIRCULOS) {
          msg.setText("Primitivo: CIRCULO (" + e.getX() + ", " + e.getY() + ")");
      }
      else {
          msg.setText("Primitivo: NENHUM (" + e.getX() + ", " + e.getY() + ")");
      }
  }

  public void desenharPrimitivos(Graphics g) {

    if ((tipo == TiposPrimitivos.RETAS) && (p1 != null) && (p2 != null)) {
      if (rOld != null) {
        rOld.setCor(Color.WHITE);
        //rOld.desenharReta(g);
      }
      
      Reta2D r = new Reta2D(p1, p2);
      rOld = r;
      r.desenharReta(g);
    }

    if ((tipo == TiposPrimitivos.CIRCULOS) && 
      (p1 != null) && (p2 != null)) {
      Circulo2D r = new Circulo2D(p1, p2.calcularDistancia(p1));
      r.desenharCirculoPolar(g);
    }
  }
}
