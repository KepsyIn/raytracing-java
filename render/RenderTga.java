package render;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RenderTga
{

    private static final String DEFAULT_FILENAME = "default_tga";
    private static final String DEFAULT_EXTENSION = "tga";
    
    // TGA File Header Constants
    private static final byte COMMENT_SIZE = 0;
    private static final byte COLORMAP_TYPE = 0;
    private static final byte IMAGE_TYPE = 2;
    private static final int ORIGIN = 0;
    private static final int LENGTH = 0;
    private static final byte DEPTH = 0;
    private static final int X_ORIGIN = 0;
    private static final int Y_ORIGIN = 0;
    private static final byte BITS_PER_PIXEL = 24;
    private static final byte DESCRIPTOR = 0;
    
    /**
     * Inner class representing TGA file header configuration
     */
    private static class TGAHeader {
        public final int width;
        public final int height;
        
        public TGAHeader(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
    
    /**
     * 
     * @param fout : output file stream
     * @param s : short to write to disc in little endian
     */
    private static void writeShort(FileOutputStream fout,int n) throws IOException
    {
        fout.write(n&255);
        fout.write((n>>8)&255);
    }

    /**
     * Write the TGA file header
     * @param fout : output file stream
     * @param header : TGA header configuration
     */
    private static void writeTGAHeader(FileOutputStream fout, TGAHeader header) throws IOException {
        fout.write(COMMENT_SIZE);
        fout.write(COLORMAP_TYPE);
        fout.write(IMAGE_TYPE);
        writeShort(fout, ORIGIN);
        writeShort(fout, LENGTH);
        fout.write(DEPTH);
        writeShort(fout, X_ORIGIN);
        writeShort(fout, Y_ORIGIN);
        writeShort(fout, header.width);
        writeShort(fout, header.height);
        fout.write(BITS_PER_PIXEL);
        fout.write(DESCRIPTOR);
    }

    /**
     * 
     * @param filename name of final TGA file
     * @param buffer buffer that contains the image. 3 bytes per pixel ordered this way : Blue, Green, Red
     * @param width Width of the image
     * @param height Height of the image
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    public static void saveTGA(String filename, byte buffer[], int width, int height) throws IOException, UnsupportedEncodingException {

        FileOutputStream fout = new FileOutputStream(new File(filename));
        TGAHeader header = new TGAHeader(width, height);
        
        writeTGAHeader(fout, header);

        /* Write the buffer */
        fout.write(buffer);

        fout.close();
    }

}

