import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import PageContainer from '../../components/PageContainer';
import Mainbar from '../../components/Mainbar';
import QuestionListWrapper from '../../components/QuestionList/QuestionListWrapper';
import { Pagination } from '../../components/Pagination';
import { getSearchList } from '../../_actions/search_action';
import PageHeader from './PageHeader';
import useQueryString from '../../util/useQueryString';

const ClearBr = styled.br`
  clear: both !important;
`;

const SearchPage = () => {
  const queryString = useQueryString().get('q');

  const dispatch = useDispatch();
  const search = useSelector((state) => state.search);
  const {
    searchInputValue,
    sortTabValue,
    pageSizerValue,
    pagerValue,
    totalPages,
    questionsList,
  } = search;
  console.log(searchInputValue);

  useEffect(() => {
    dispatch(getSearchList(search));
  }, [dispatch]);

  const onClickPageSizer = (size) => {
    const params = {
      sortTabValue,
      pagerValue: 1,
      pageSizerValue: size,
    };
    search.sortTabValue = dispatch(getSearchList(params));
  };

  const onChangePager = (page) => {
    const params = {
      sortTabValue,
      pagerValue: page,
      pageSizerValue,
    };
    search.sortTabValue = dispatch(getSearchList(params));
  };

  return (
    <PageContainer nav sidebar={queryString ? true : false} footer>
      <Mainbar>
        <PageHeader queryString={queryString} />
        {queryString ? (
          <>
            <QuestionListWrapper
              className="questions"
              questionsList={questionsList}
            />
            <ClearBr />
            <Pagination
              size={pageSizerValue}
              totalPages={totalPages}
              currentPage={pagerValue}
              onClickPageSizer={onClickPageSizer}
              onChangePager={onChangePager}
            />
          </>
        ) : null}
      </Mainbar>
    </PageContainer>
  );
};
export default SearchPage;
