package burp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TarlogicLogo extends JLabel
{
	public TarlogicLogo
	outer ()
	{
		return this;
	}
	
	public
	TarlogicLogo (String caption)
	{
		super (caption);
		
		
		this.addMouseListener
		(
			new MouseAdapter() 
			{
				@Override
				public void mouseClicked (MouseEvent e) 
				{
					//CustomTools.openURL ("https://github.com/winezer0");
				}
			}
        );
		
		try
		{
			this.setIcon(CustomTools.loadIcon ("tarlogic-small.png"));
		}
		catch (Exception e)
		{
		}
		
		this.setCursor (Cursor.getPredefinedCursor (Cursor.HAND_CURSOR));
	}
}
