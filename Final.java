package pkgfinal;

/**Final-Piano Tiles
 * Date:11/27/2018
 * Name:Yifei Feng
 * Using GUI to create Piano Tiles
 */

public class Final {

    public static void main(String[] args) {
        game g=new game();
        g.setVisible(true);
        //making keylistener work
        g.setFocusable(true);
        g.requestFocusInWindow();
    }
    
}
