import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import PageHeader from './PageHeader';
import SeUql from './SeUql';
import PageContainer from '../../components/PageContainer';
import Mainbar from '../../components/Mainbar';
import QuestionListWrapper from '../../components/QuestionList/QuestionListWrapper';
import { Pagination } from '../../components/Pagination';
import { getAllQuestions } from '../../_actions/questions_action';

const ClearBr = styled.br`
  clear: both !important;
`;

const QuestionsPage = () => {
  const dispatch = useDispatch();
  const allQuestions = useSelector((state) => state.allQuestions);
  const {
    sortTabValue,
    pageSizerValue,
    pagerValue,
    totalPages,
    questionsList,
  } = allQuestions;

  useEffect(() => {
    dispatch(getAllQuestions(allQuestions));
  }, [dispatch]);

  const onClickPageSizer = (size) => {
    const params = {
      sortTabValue,
      pagerValue: 1,
      pageSizerValue: size,
    };
    allQuestions.sortTabValue = dispatch(getAllQuestions(params));
  };

  const onChangePager = (page) => {
    const params = {
      sortTabValue,
      pagerValue: page,
      pageSizerValue,
    };
    allQuestions.sortTabValue = dispatch(getAllQuestions(params));
  };

  return (
    <PageContainer nav sidebar footer>
      <Mainbar role="main" aria-labelledby="h-all-questions">
        <PageHeader />
        <SeUql />
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
      </Mainbar>
    </PageContainer>
  );
};
export default QuestionsPage;
