package es.cadox8.deud.ui.components.block;

import es.cadox8.deud.ui.UiComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UiBlock extends UiComponent {

    private Color background;

    private boolean fill;

    private boolean rounded;
    private int roundRadius;

    public UiBlock() {
        this(Color.darkGray);
    }

    public UiBlock(Color background) {
        this(background, true);
    }

    public UiBlock(Color background, boolean fill) {
        this(background, fill, false, 35);
    }

    public UiBlock(Color background, boolean fill, boolean rounded, int roundRadius) {
        super();
        this.background = background;

        this.fill = fill;

        this.rounded = rounded;
        this.roundRadius = roundRadius;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.background);
        final Rectangle bounds = this.getUiDimension().getBounds();

        final RoundRectangle2D r = new RoundRectangle2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), 0, 0);

        if (this.isRounded()) r.setRoundRect(new RoundRectangle2D.Double(r.getX(), r.getY(), r.getWidth(), r.getHeight(), roundRadius, roundRadius));

        g2.draw(r);

        if (this.isFill()) g2.fill(r);
    }

    @Override
    public void onClick() {

    }

    public void setRoundRadius(int roundRadius) {
        if (roundRadius < 0 || roundRadius > 100) throw new IllegalArgumentException("The round radius must be between 0 and 100 (" + roundRadius + ")");
        this.setRounded(true);
        this.roundRadius = roundRadius;
    }
}
