import styled from 'styled-components';
import PageHeader from './PageHeader';
import SeUql from './SeUql';
import PageContainer from '../../components/PageContainer';
import Mainbar from '../../components/Mainbar';
import QuestionListWrapper from '../../components/QuestionList/QuestionListWrapper';
import { Pagination } from '../../components/Pagination';

const ClearBr = styled.br`
  clear: both !important;
`;

const QuestionsPage = () => {
  return (
    <PageContainer nav sidebar footer>
      <Mainbar role="main" aria-labelledby="h-all-questions">
        <PageHeader />
        <SeUql />
        <QuestionListWrapper className="questions" />
        <ClearBr />
        <Pagination />
      </Mainbar>
    </PageContainer>
  );
};
export default QuestionsPage;
