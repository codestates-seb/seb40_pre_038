import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import PageContainer from '../../../components/PageContainer';
import Mainbar from '../../../components/Mainbar';
import { getAllQuestions } from '../../../_actions/search_action';
import PageHeader from './PageHeader';

const SearchResultsPage = () => {
  const dispatch = useDispatch();
  const allQuestions = useSelector((state) => state.search.allQuestions);

  useEffect(() => {
    dispatch(getAllQuestions(allQuestions));
  }, [dispatch]);

  return (
    <PageContainer nav footer>
      <Mainbar>
        <PageHeader />
      </Mainbar>
    </PageContainer>
  );
};
export default SearchResultsPage;
