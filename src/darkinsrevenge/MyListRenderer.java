/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkinsrevenge;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Jam
 */
class MyListRenderer extends DefaultListCellRenderer
    {
        private HashMap theChosen = new HashMap();
  
        public Component getListCellRendererComponent( JList list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus )
        {
            super.getListCellRendererComponent( list, value, index,
                    isSelected, cellHasFocus );
  
            setForeground( Color.white );
  
            return( this );
        }
    }
  
    class ExitHandler extends WindowAdapter
    {
        public void windowClosing( WindowEvent e )
        {
            System.exit( 0 );
        }
    }
