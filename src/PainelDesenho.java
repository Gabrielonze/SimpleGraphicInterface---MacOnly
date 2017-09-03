import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
  JLabel msg;
  TiposPrimitivos tipo;
  Ponto2D p1;
  Ponto2D p2;
  List<Reta2D> retas = new ArrayList<Reta2D>();
  List<Circulo2D> circulos = new ArrayList<Circulo2D>();

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
      p2 = null;
    }
  }

  public void mouseReleased(MouseEvent e) {

    if (tipo == TiposPrimitivos.RETAS) {
      retas.add(new Reta2D(p1, p2));
    }
    else if (tipo == TiposPrimitivos.CIRCULOS) {
      circulos.add(new Circulo2D(p1, p2.calcularDistancia(p1)));
    }
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

  public void repaintAll(Graphics g) {
    for(Reta2D r : retas) {
      r.desenharReta(g);
    }
    for(Circulo2D c : circulos) {
      c.desenharCirculoPolar(g);
    }
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
      Reta2D r = new Reta2D(p1, p2);
      r.desenharReta(g);
    }

    if ((tipo == TiposPrimitivos.CIRCULOS) && (p1 != null) && (p2 != null)) {
      Circulo2D r = new Circulo2D(p1, p2.calcularDistancia(p1));
      r.desenharCirculoPolar(g);
    }

    repaintAll(g);

  }

}
