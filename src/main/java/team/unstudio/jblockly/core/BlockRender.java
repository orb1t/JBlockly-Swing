package team.unstudio.jblockly.core;

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.VLineTo;

public class BlockRender {

	public Path getPathFromSVG(String svg){
		Path path = new Path();
		int i=0;
		while(i<svg.length()){
			if(Character.isLetter(svg.charAt(i))){
				int j=i+1;
				while(j<svg.length()){
					if(Character.isLetter(svg.charAt(j))) break;
					j++;
				}
				path.getElements().addAll(getPathElementFromSVG(svg.substring(i, j)));
			}
			i++;
		}
		return path;
	}
	
	public PathElement[] getPathElementFromSVG(String svg){
		
		String args[] = svg.substring(1).replaceAll(",", " ").split(" ");
		double points[] = new double[args.length];
		for(int i=0;i<args.length;i++) points[i] = Double.parseDouble(args[i]);

		switch(svg.charAt(0)){
			case 'M':
			case 'm':{
					PathElement e[] = new PathElement[points.length/2];
					for(int i=0;i<e.length;i++) e[i] = new MoveTo(points[i*2], points[i*2+1]);
					return e;
				}
			case 'L':
			case 'l':{
					PathElement e[] = new PathElement[points.length/2];
					for(int i=0;i<e.length;i++) e[i] = new LineTo(points[i*2], points[i*2+1]);
					return e;
				}
			case 'H':
			case 'h':{
					PathElement e[] = new PathElement[points.length];
					for(int i=0;i<e.length;i++) e[i] = new HLineTo(points[i]);
					return e;
				}
			case 'V':
			case 'v':{
					PathElement e[] = new PathElement[points.length];
					for(int i=0;i<e.length;i++) e[i] = new VLineTo(points[i]);
					return e;
				}
			case 'C':
			case 'c':
			case 'S':
			case 's':{
				PathElement e[] = new PathElement[points.length/6];
				for(int i=0;i<e.length;i++) e[i] = new CubicCurveTo(points[i*6],points[i*6+1],points[i*6+2],points[i*6+3],points[i*6+4],points[i*6+5]);
				return e;
				}
			case 'Q':
			case 'q':
			case 'T':
			case 't':{
				PathElement e[] = new PathElement[points.length/4];
				for(int i=0;i<e.length;i++) e[i] = new QuadCurveTo(points[i*4],points[i*4+1],points[i*4+2],points[i*4+3]);
				return e;
				}
			case 'A':
			case 'a':{
					PathElement e[] = new PathElement[points.length/7];
					for(int i=0;i<e.length;i++) e[i] = new ArcTo(points[i*7], points[i*7+1], points[i*7+2], points[i*7+5], points[i*7+6], points[i*7+4]==1, points[i*7+3]==1);
					return e;
				}
			case 'Z':
			case 'z':{
					return new PathElement[]{new ClosePath()};
				}
			default: 
				return new PathElement[0];
		}
	}
}