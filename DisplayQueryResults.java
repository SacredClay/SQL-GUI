/* Name: Mehrob Farhangmehr
 Course: CNT 4714 Summer 2020
 Assignment title: Project Three: Two-Tier Client-Server Application Development With MySQL and JDBC
 Due Date: July 26, 2020
*/
// Display the results of queries against the bikes table in the bikedb database.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;


public class DisplayQueryResults extends JFrame 
{
   // default query retrieves all data from bikes table
   static final String DEFAULT_QUERY = "SELECT * FROM riders LIMIT 1";
   
   private ResultSetTableModel tableModel;
   private JTextArea queryArea;
   private JTextField textFieldUsername;
   private JPasswordField passwordField;
   private String Username;
   private String Password;
   
   // create ResultSetTableModel and GUI
   public DisplayQueryResults() 
   {   
      super( "SQL Client App - Mehrob Farhangmehr (CNT 4714 - Summer 2020)" );
        
      // create ResultSetTableModel and display database table
      try 
      {
         // create TableModel for results of query SELECT * FROM bikes
    	 textFieldUsername = new JTextField();
         tableModel = new ResultSetTableModel();
 
         
         // create Box to manage placement of queryArea and 
         // submitButton in GUI
         Box box = Box.createHorizontalBox();
         box.setBounds(426, 22, 313, 90);

         
         // create JTable delegate for tableModel 
         JTable resultTable = new JTable( tableModel );
         resultTable.setGridColor(Color.BLACK);
         getContentPane().setLayout(null);
         
         // place GUI components on content pane
         getContentPane().add( box );
         JScrollPane scrollPane_1 = new JScrollPane( resultTable );
         scrollPane_1.setBounds(0, 245, 739, 135);
         getContentPane().add( scrollPane_1 );
         
         
         JPanel panel = new JPanel();
         panel.setBounds(0, 190, 756, 23);
         getContentPane().add(panel);
         panel.setLayout(null);
         
         // set up JButton for submitting queries
         JButton submitButton = new JButton( "Submit Query" );
         submitButton.setBounds(611, 0, 135, 23);
         panel.add(submitButton);
         submitButton.setBackground(Color.BLUE);
         submitButton.setForeground(Color.YELLOW);
         submitButton.setBorderPainted(false);
         submitButton.setOpaque(true);
         
         JButton btnConnect = new JButton("Connect to Database");
         btnConnect.setBounds(259, 0, 156, 23);
         btnConnect.setBackground(Color.GREEN);
         btnConnect.setForeground(Color.BLACK);
         panel.add(btnConnect);
         
         JLabel lblConnectionStatus = new JLabel("No Connection Now");
         lblConnectionStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
         lblConnectionStatus.setBackground(Color.BLACK);
         lblConnectionStatus.setForeground(Color.RED);
         lblConnectionStatus.setOpaque(true);
         lblConnectionStatus.setBounds(10, 0, 239, 23);
         panel.add(lblConnectionStatus);
         
         JButton btnClearSqlCommand = new JButton("Clear SQL Command");
         btnClearSqlCommand.setFont(new Font("Tahoma", Font.PLAIN, 11));
         btnClearSqlCommand.setBounds(446, 0, 135, 23);
         btnClearSqlCommand.setBackground(Color.WHITE);
         btnClearSqlCommand.setForeground(Color.RED);
         panel.add(btnClearSqlCommand);
         
         JLabel lblInformation = new JLabel("Enter Database Information");
         lblInformation.setFont(new Font("Tahoma", Font.BOLD, 11));
         lblInformation.setForeground(Color.BLUE);
         lblInformation.setBounds(10, 0, 174, 23);
         getContentPane().add(lblInformation);
         
         JLabel lblResult = new JLabel("SQL Execution Result Window");
         lblResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblResult.setForeground(Color.BLUE);
         lblResult.setBounds(0, 224, 195, 23);
         getContentPane().add(lblResult);
         
         JLabel lblCommand = new JLabel("Enter A SQL Command");
         lblCommand.setForeground(Color.BLUE);
         lblCommand.setBounds(426, 0, 232, 23);
         getContentPane().add(lblCommand);
         
         JLabel lblDriver = new JLabel("JDBC Driver");
         lblDriver.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblDriver.setBackground(Color.GRAY);
         lblDriver.setOpaque(true);
         lblDriver.setBounds(20, 34, 94, 22);
         getContentPane().add(lblDriver);
         
         JLabel lblURL = new JLabel("Database URL");
         lblURL.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblURL.setBackground(Color.GRAY);
         lblURL.setOpaque(true);
         lblURL.setBounds(20, 67, 94, 22);
         getContentPane().add(lblURL);
         
         JLabel lblUsername = new JLabel("Username");
         lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblUsername.setBackground(Color.GRAY);
         lblUsername.setOpaque(true);
         lblUsername.setBounds(20, 100, 94, 22);
         getContentPane().add(lblUsername);
         
         JLabel lblPassword = new JLabel("Password");
         lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblPassword.setBackground(Color.GRAY);
         lblPassword.setOpaque(true);
         lblPassword.setBounds(20, 133, 94, 22);
         getContentPane().add(lblPassword);
         
         textFieldUsername = new JTextField();
         textFieldUsername.setBounds(115, 100, 231, 20);
         getContentPane().add(textFieldUsername);
         textFieldUsername.setColumns(10);
         
         passwordField = new JPasswordField();
         passwordField.setColumns(10);
         passwordField.setBounds(115, 133, 231, 20);
         getContentPane().add(passwordField);
         
         JComboBox comboBoxDriver = new JComboBox();
         comboBoxDriver.setModel(new DefaultComboBoxModel(new String[] {"com.mysql.cj.jdbc.Driver"}));
         comboBoxDriver.setBounds(115, 34, 230, 22);
         getContentPane().add(comboBoxDriver);
         
         JComboBox comboBoxURL = new JComboBox();
         comboBoxURL.setModel(new DefaultComboBoxModel(new String[] {"jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC"}));
         comboBoxURL.setBounds(115, 67, 231, 22);
         getContentPane().add(comboBoxURL);
         
         JButton btnClearResult = new JButton("Clear Result Window");
         btnClearResult.setBounds(10, 391, 155, 23);
         btnClearResult.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnClearResult.setBackground(Color.YELLOW);
         btnClearResult.setForeground(Color.BLACK);
         getContentPane().add(btnClearResult);
         
                  // set up JTextArea in which user types queries
         			queryArea = new JTextArea( 3, 800);
         			//queryArea = new JTextArea( DEFAULT_QUERY, 3, 100 );
         			queryArea.setWrapStyleWord( true );
         			queryArea.setLineWrap( true );
         			
         			JScrollPane scrollPane = new JScrollPane( queryArea,
         			   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
         			   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
         			scrollPane.setBounds(426, 22, 313, 160);
         			getContentPane().add(scrollPane);

         // create event listener for submitButton
         submitButton.addActionListener( 
         
            new ActionListener() 
            {
               // pass query to table model
               public void actionPerformed( ActionEvent event )
               {
                  // perform a new query
                  try 
                  {
                     tableModel.setQuery( queryArea.getText() );
                  } // end try
                  catch ( SQLException sqlException ) 
                  {
                     JOptionPane.showMessageDialog( null, 
                        sqlException.getMessage(), "Database error", 
                        JOptionPane.ERROR_MESSAGE );
                     
                     // try to recover from invalid user query 
                     // by executing default query                 
                  } // end outer catch
               } // end actionPerformed
            }  // end ActionListener inner class          
         ); // end call to addActionListener
         
         btnConnect.addActionListener( 
                 
                 new ActionListener() 
                 {
                    // pass query to table model
                    public void actionPerformed( ActionEvent event )
                    {
                    	tableModel.connection(comboBoxURL.getSelectedItem().toString(), textFieldUsername.getText(), passwordField.getText());
                    	lblConnectionStatus.setText("Connected to " + comboBoxURL.getSelectedItem().toString());
                    } // end actionPerformed
                 }  // end ActionListener inner class          
              ); // end call to addActionListener
         
         btnClearSqlCommand.addActionListener( 
                 
                 new ActionListener() 
                 {
                    // pass query to table model
                    public void actionPerformed( ActionEvent event )
                    {
                    	queryArea.setText("");
                    } // end actionPerformed
                 }  // end ActionListener inner class          
              ); // end call to addActionListener
         
         btnClearResult.addActionListener( 
                 
                 new ActionListener() 
                 {
                    // pass query to table model
                    public void actionPerformed( ActionEvent event )
                    {
                    	tableModel.clearResults();
                    } // end actionPerformed
                 }  // end ActionListener inner class          
              ); // end call to addActionListener

         setSize( 780, 462 ); // set window size
         setVisible( true ); // display window  
      } // end try
      catch ( ClassNotFoundException classNotFound ) 
      {
         JOptionPane.showMessageDialog( null, 
            "MySQL driver not found", "Driver not found",
            JOptionPane.ERROR_MESSAGE );
         
         System.exit( 1 ); // terminate application
      } // end catch
      catch ( SQLException sqlException ) 
      {
         JOptionPane.showMessageDialog( null, sqlException.getMessage(), 
            "Database error", JOptionPane.ERROR_MESSAGE );
               
         // ensure database connection is closed
         tableModel.disconnectFromDatabase();
         
         System.exit( 1 );   // terminate application
      } // end catch
      
      // dispose of window when user quits application (this overrides
      // the default of HIDE_ON_CLOSE)
      setDefaultCloseOperation( DISPOSE_ON_CLOSE );
      
      // ensure database connection is closed when user quits application
      addWindowListener(new WindowAdapter() 
         {
            // disconnect from database and exit when window has closed
            public void windowClosed( WindowEvent event )
            {
               tableModel.disconnectFromDatabase();
               System.exit( 0 );
            } // end method windowClosed
         } // end WindowAdapter inner class
      ); // end call to addWindowListener
   } // end DisplayQueryResults constructor
   
   // execute application
   public static void main( String args[] ) 
   {
      new DisplayQueryResults();     
   } // end main
} // end class DisplayQueryResults



