package render;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RenderTga extends ImageRenderer {

    private static final String DEFAULT_EXTENSION = "tga";
    private static final String FORMAT_NAME = "TGA";
    
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
    
    private static class TGAHeader {
        public final int width;
        public final int height;
        
        public TGAHeader(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
    
    private static void writeShort(FileOutputStream fout,int n) throws IOException
    {
        fout.write(n&255);
        fout.write((n>>8)&255);
    }

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

    @Override
    public void save(String filename, byte buffer[], int width, int height) throws IOException {

        FileOutputStream fout = new FileOutputStream(new File(filename));
        TGAHeader header = new TGAHeader(width, height);
        
        writeTGAHeader(fout, header);

        fout.write(buffer);

        fout.close();
    }

    public static void saveTGA(String filename, byte buffer[], int width, int height) throws IOException {
        RenderTga renderer = new RenderTga();
        renderer.save(filename, buffer, width, height);
    }
    
    @Override
    protected String getExtension() {
        return DEFAULT_EXTENSION;
    }
    
    @Override
    protected String getFormatName() {
        return FORMAT_NAME;
    }

}

