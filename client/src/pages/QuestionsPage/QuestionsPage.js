import PageContainer from '../../components/PageContainer';
import { Mainbar } from '../../components/Mainbar';
import PageHeader from './PageHeader';
import SeUql from './SeUql';
import Questions from './Questions';
import Pagination from './Pagination';

const QuestionsPage = () => {
  return (
    <PageContainer nav sidebar footer>
      <Mainbar role="main" aria-labelledby="h-all-questions">
        <PageHeader />
        <SeUql />
        <Questions />
        <br className="clear-both" />
        <Pagination />
      </Mainbar>
    </PageContainer>
  );
};
export default QuestionsPage;
