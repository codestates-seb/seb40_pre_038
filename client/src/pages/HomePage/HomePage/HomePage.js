import PageContainer from '../../../components/PageContainer';
import TopQuestionsPage from './TopQuestionsPage';

const HomePage = () => {
  return (
    <PageContainer nav sidebar footer>
      <TopQuestionsPage />
    </PageContainer>
  );
};

export default HomePage;
