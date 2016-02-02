

package Controller;



import java.util.EventListener;
/** 
 * This interface is used to pass information from View to Model by using event
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */

public interface PanelListener extends EventListener {

    void panelEventOccurred(PanelEvent e);
}
