/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author pedro.hlaredes
 *
 */
public class Tile_layer extends JPanel implements Desenhavel {

    private int[][] mapa;
    private BufferedImage tileSheet, minitaur;
    //Tile_layer minotaur = null;

    public Tile_layer(int[][] existingMap) {
        mapa = new int[existingMap.length][existingMap[0].length];
        for (int y = 0; y < mapa.length; y++) {
            for (int x = 0; x < mapa[y].length; x++) {
                mapa[y][x] = existingMap[y][x];
            }
        }
        tileSheet = LoadTileSheet("/pics/blocks.png");
    }

    //Pega o tamanho do mapa
    public Tile_layer(int width, int height) {
        mapa = new int[height][width];
    }

    //Le o mapa atraves de um txt
    public static Tile_layer From_file(String fileName) {
        Tile_layer layer = null;
        ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.isEmpty()) {
                    continue;
                }
                ArrayList<Integer> row = new ArrayList<>();
                String[] values = currentLine.trim().split(" ");

                for (String string : values) {
                    if (!string.isEmpty()) {
                        int id = Integer.parseInt(string);
                        row.add(id);
                    }
                }
                tempLayout.add(row);
            }
        } catch (IOException e) {

        }
        int width = tempLayout.get(0).size();
        int height = tempLayout.size();

        layer = new Tile_layer(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                layer.mapa[y][x] = tempLayout.get(y).get(x);
            }
        }
        layer.tileSheet = layer.LoadTileSheet("blocks.png");
        //layer.minitaur = layer.LoadTileSheet("minitaur.png");

        return layer;
    }

    public BufferedImage LoadTileSheet(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.print("Nao consegui ler a imagem!");
        }
        return img;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int y = 0; y < mapa.length; y++) {
            for (int x = 0; x < mapa[y].length; x++) {
                int index = mapa[y][x];
                int yOffset = 0;
                if (index > (tileSheet.getWidth() / Engine.TILE_WIDTH) - 1) {
                    yOffset++;
                    index = index - (tileSheet.getWidth() / Engine.TILE_WIDTH);
                }
                g.drawImage(tileSheet, x * Engine.TILE_WIDTH,
                        y * Engine.TILE_HEIGTH,
                        (x * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
                        (y * Engine.TILE_HEIGTH) + Engine.TILE_HEIGTH,
                        index * Engine.TILE_WIDTH,
                        yOffset * Engine.TILE_HEIGTH,
                        (index * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
                        (yOffset * Engine.TILE_HEIGTH) + Engine.TILE_HEIGTH, null);
            }
        }
    }
}
