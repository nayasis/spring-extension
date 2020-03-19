package io.nayasis.spring.extension.sql.phase.element;

import io.nayasis.basica.base.Strings;
import io.nayasis.spring.extension.sql.phase.element.abstracts.SqlElement;
import org.springframework.expression.Expression;

import java.util.List;

public class WhenFirstSqlElement extends IfSqlElement {

	public WhenFirstSqlElement( String testExpression, List<SqlElement> children ) {
		super( testExpression, children );
	}

	public WhenFirstSqlElement( Expression testExpression, List<SqlElement> children ) {
		super( testExpression, children );
	}

	private void toString( StringBuilder buffer, SqlElement node, int depth ) {

		String tab = Strings.lpad( "", depth * 2, ' ' );

		buffer.append( String.format( "%s%s", tab, node.toString() ) );

	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append( String.format("[WHEN test='%s']\n", getTestExpression()) );

		for( SqlElement node : children ) {
			toString( sb, node, 1 );
		}

		return sb.toString();

	}

}
