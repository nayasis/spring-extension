package io.nayasis.spring.extension.sql.phase.element;

import io.nayasis.basica.base.Strings;
import io.nayasis.spring.extension.sql.entity.QueryParameter;
import io.nayasis.spring.extension.sql.phase.element.abstracts.SqlElement;

public class RefSqlElement extends SqlElement {

	private String referenceSqlId = null;

	public RefSqlElement( String referenceSqlId ) {
		if( Strings.isEmpty( referenceSqlId ) ) return;
		this.referenceSqlId = referenceSqlId;
	}

	/**
	 * MainId가 세팅되지 않은 참조ID는
	 * Root Sql MainId 가 세팅될 경우, 이에 맞도록 참조ID를 변경시킨다.
	 *
	 * @param mainId main id of sql id.
	 */
	public void includeMainId( String mainId ) {
		if( referenceSqlId != null && ! referenceSqlId.contains( "." ) ) {
			referenceSqlId = String.format( "%s.%s", mainId, referenceSqlId );
		}
	}

	@Override
    public String toString( QueryParameter param ) {

		if( referenceSqlId == null ) return "";

		return "";

		//TODO : SqlRepository 구현

//		SqlNode sql = SqlRepository.get( referenceSqlId );
//
//		if( sql == null ) throw new SqlConfigurationException( "refId[{}] is not exists.", referenceSqlId );
//
//		return sql.getText( param );


	}

	public String toString() {
		return referenceSqlId == null ? "" : String.format( "<ref id=\"%s\"/>", referenceSqlId );
	}

}
