import PageContainer from '../../components/PageContainer';
import { Mainbar } from '../../components/Mainbar';
import PageHeader from './PageHeader';
import SeUql from './SeUql';
import QuestionListWrapper from '../../components/QuestionList/QuestionListWrapper';
import Pagination from './Pagination';

const QuestionsPage = () => {
  return (
    <PageContainer nav sidebar footer>
      <Mainbar role="main" aria-labelledby="h-all-questions">
        <PageHeader />
        <SeUql />
        <QuestionListWrapper className="questions" />
        <br className="clear-both" />
        <Pagination />
      </Mainbar>
    </PageContainer>
  );
};
export default QuestionsPage;
