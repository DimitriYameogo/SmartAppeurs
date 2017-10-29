/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esprit
 */
import com.rando.entities.Event;
import com.rando.services.CRUDevent;
import com.rando.techniques.ConnectionRando;
import java.util.List;
public class testcrud {
     public static void main(String[] args) {
        // TODO code application logic here
        ConnectionRando  cnx = ConnectionRando.getInstance();
    CRUDevent tools = new CRUDevent();
//   Event e = new Event(1,10,"yooogjhg","hgfhgfhg","hgjhg","1955-10-20",2,"gkjgkj","kgjk",2f);
// tools.modifierEvent(e);
//List <Event> r=tools.displayAll();
// System.out.println(r);
  tools.supprimerEvent(1);
    }
}
