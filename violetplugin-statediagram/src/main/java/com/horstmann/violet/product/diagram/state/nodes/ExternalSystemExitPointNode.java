package com.horstmann.violet.product.diagram.state.nodes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import com.horstmann.violet.framework.graphics.content.*;
import com.horstmann.violet.framework.graphics.shape.ContentInsideCustomShape;
import com.horstmann.violet.framework.graphics.shape.ContentInsideRectangle;
import com.horstmann.violet.product.diagram.abstracts.node.ColorableNode;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.property.string.LineText;
import com.horstmann.violet.product.diagram.abstracts.property.string.SingleLineText;

public class ExternalSystemExitPointNode extends ColorableNode
{
	protected static class ExternalSystemEntryPointShape implements ContentInsideCustomShape.ShapeCreator
	{
		public ExternalSystemEntryPointShape(TextContent nameContent)
		{
			super();
			this.nameContent = nameContent;
		}

		@Override
		public Shape createShape(int contentWidth, int contentHeight)
		{
			GeneralPath path = new GeneralPath();
			path.append(new Ellipse2D.Float(0,0,DEFAULT_DIAMETER,DEFAULT_DIAMETER), false);

			float p = (float) (0.5+(DEFAULT_DIAMETER/2) - ((DEFAULT_DIAMETER/2)/(Math.sqrt(2))));

			path.moveTo(p,p);
			path.lineTo(DEFAULT_DIAMETER-p,DEFAULT_DIAMETER-p);
			path.moveTo(DEFAULT_DIAMETER-p,p);
			path.lineTo(p,DEFAULT_DIAMETER-p);

//			AffineTransform af = new AffineTransform();
//			af.translate(((double)(contentWidth - nameContent.getWidth()) / 2), 0);
//			shape.transform(af);

			return path;
		}

		private TextContent nameContent;
	}
	/**
	 * Construct an actor node_old with a default size and name
	 */
	public ExternalSystemExitPointNode()
	{
		super();
		name = new SingleLineText();
		name.setAlignment(LineText.CENTER);
		name.setPadding(10,5,5,5);
		createContentStructure();
	}

	protected ExternalSystemExitPointNode(ExternalSystemExitPointNode node) throws CloneNotSupportedException
	{
		super(node);
		name = node.name.clone();
		createContentStructure();
	}

	@Override
	public void deserializeSupport()
	{
		super.deserializeSupport();
		name.deserializeSupport();
		name.setAlignment(LineText.CENTER);
		name.setPadding(10,5,5,5);
	}

	@Override
	protected void createContentStructure()
	{
		EmptyContent emptyContent = new EmptyContent();
		TextContent nameContent = new TextContent(name);

		emptyContent.setMinWidth(DEFAULT_DIAMETER);
		emptyContent.setMinHeight(DEFAULT_DIAMETER);

		ContentInsideCustomShape contentInsideCustomShape = new ContentInsideCustomShape(emptyContent, new ExternalSystemEntryPointShape(nameContent));;
		setBorder(new ContentBorder(contentInsideCustomShape, getBorderColor()));
		setBackground(new ContentBackground(getBorder(), getBackgroundColor()));

		VerticalLayout verticalGroupContent = new VerticalLayout();
		verticalGroupContent.add(getBackground());
		verticalGroupContent.add(nameContent);

		setContent(new ContentInsideRectangle(verticalGroupContent));

		setTextColor(super.getTextColor());
	}

	@Override
	protected INode copy() throws CloneNotSupportedException
	{
		return new ExternalSystemExitPointNode(this);
	}

	/**
	 * text label near node
	 */
	protected SingleLineText name;
	/** default node diameter */
	private static int DEFAULT_DIAMETER = 14;
}