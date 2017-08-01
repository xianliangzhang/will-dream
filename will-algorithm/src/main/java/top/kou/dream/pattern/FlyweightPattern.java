package top.kou.dream.pattern;

/**
 * 共享模式（享元模式）：适用于大量小对象共享
 * Created by ZXL on 2017/8/1.
 */
public class FlyweightPattern {
    static class Window {}
    static class Font {}
    static class GlyphContext {
        private Font[] fonts = new Font[]{};
        private int index = 0;

        void next(int step) {
            index += step;
            if (index > 0) {
                index = fonts.length % index;
            }
        }
    }

    static abstract class Glyph {
        abstract void draw(Window window, GlyphContext context);

        abstract void setFont(Font font, GlyphContext context);
        abstract Font getFont(GlyphContext context);

        abstract void first(GlyphContext context);
        abstract void next(GlyphContext context);
        abstract boolean isDone(GlyphContext context);
        abstract Glyph Current(GlyphContext context);

        abstract void insert(Glyph glyph, GlyphContext context);
        abstract void remove(GlyphContext context);
    }

    static class CharacterGlyph extends Glyph {
        private Character character;
        CharacterGlyph(Character character) {
            this.character = character;
        }

        @Override
        void draw(Window window, GlyphContext context) {
            System.out.println("CharacterGlyph - Draw...");
        }

        @Override
        void setFont(Font font, GlyphContext context) {

        }

        @Override
        Font getFont(GlyphContext context) {
            return null;
        }

        @Override
        void first(GlyphContext context) {

        }

        @Override
        void next(GlyphContext context) {

        }

        @Override
        boolean isDone(GlyphContext context) {
            return false;
        }

        @Override
        Glyph Current(GlyphContext context) {
            return null;
        }

        @Override
        void insert(Glyph glyph, GlyphContext context) {

        }

        @Override
        void remove(GlyphContext context) {

        }
    }
}
