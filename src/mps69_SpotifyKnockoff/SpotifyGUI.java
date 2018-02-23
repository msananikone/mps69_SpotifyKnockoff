/* Monica Sananikone
 * Note: Upon running the GUI, the window looks all crazy but if you click the view _____ buttons it will sort itself out!
 */

package mps69_SpotifyKnockoff;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpotifyGUI {

	private JFrame frame;
	private JTextField txtSearch;
	private JRadioButton radShowAlbums;
	private JRadioButton radShowArtists;
	private JRadioButton radShowSongs;
	private JTable tblData;
	private DefaultTableModel musicData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpotifyGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Spotify spotify = Spotify.getInstance();

		frame = new JFrame("Spotify (Knockoff) by mps69");
		frame.setBounds(100, 100, 1000, 600);
		frame.getContentPane().setLayout(null);

		JLabel lblViewSelector = new JLabel("Select View");
		lblViewSelector.setBounds(20, 30, 99, 16);
		frame.getContentPane().add(lblViewSelector);

		// ALBUMS
		radShowAlbums = new JRadioButton("View Albums");
		radShowAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radShowAlbums.isSelected()) {
					radShowSongs.setSelected(false);
					radShowArtists.setSelected(false);
					musicData = spotify.searchAlbums("");
					tblData.setModel(musicData);
				}
			}
		});
		radShowAlbums.setBounds(40, 60, 150, 25);
		radShowAlbums.setSelected(true);
		frame.getContentPane().add(radShowAlbums);
		// END ALBUMS

		// ARTISTS
		radShowArtists = new JRadioButton("View Artists");
		radShowArtists.setBounds(40, 85, 150, 25);
		frame.getContentPane().add(radShowArtists);
		radShowArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radShowArtists.isSelected()) {
					radShowAlbums.setSelected(false);
					radShowSongs.setSelected(false);
					musicData = spotify.searchArtists("");
					tblData.setModel(musicData);
				}
			}
		});
		// END ARTISTS

		// SONGS
		radShowSongs = new JRadioButton("View Songs");
		radShowSongs.setBounds(40, 110, 150, 25);
		frame.getContentPane().add(radShowSongs);
		radShowSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radShowSongs.isSelected()) {
					radShowAlbums.setSelected(false);
					radShowArtists.setSelected(false);
					musicData = spotify.searchSongs("");
					tblData.setModel(musicData);
				}
			}
		});
		// END SONGS

		// SEARCH
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(20, 290, 100, 20);
		frame.getContentPane().add(lblSearch);

		frame.getContentPane().add(lblViewSelector);
		txtSearch = new JTextField();
		txtSearch.setBounds(20, 315, 200, 30);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		tblData = new JTable(musicData);
		tblData.setBounds(299, 45, 600, 400);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		frame.getContentPane().add(tblData);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radShowAlbums.isSelected()) {
					musicData = spotify.searchAlbums(txtSearch.getText());
					tblData.setModel(musicData);
				} else if (radShowSongs.isSelected()) {
					musicData = spotify.searchSongs(txtSearch.getText());
					tblData.setModel(musicData);
				} else if (radShowArtists.isSelected()) {
					musicData = spotify.searchArtists(txtSearch.getText());
					tblData.setModel(musicData);
				}
			}
		});
		btnSearch.setBounds(103, 357, 117, 29);
		// END SEARCH

		musicData = spotify.searchAlbums(""); // shows this upon running
		tblData = new JTable(musicData);
		tblData.setBounds(298, 0, 1238, 801);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		frame.getContentPane().add(tblData);

		frame.getContentPane().add(btnSearch);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
