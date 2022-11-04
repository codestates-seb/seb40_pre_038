import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Button } from '../../../components/Buttons';
import { Headline } from '../../../components/Headline';
import SearchTips from '../SearchTips';

const HeadlinkWrapper = styled.div`
  display: flex !important;
  margin-bottom: 24px !important;
  align-items: flex-start !important;
  justify-content: space-between !important;
  flex-wrap: wrap !important;
`;
const HeadLineButtonWrapper = styled.div`
  display: flex !important;
  margin-left: 32px !important;
  flex: 1 auto !important;
  align-items: center !important;
  justify-content: flex-end !important;
`;

const PageHeader = () => {
  const navigate = useNavigate();

  const handleClickAskQuestionBtn = () => {
    navigate('/questions/ask');
  };

  return (
    <>
      <HeadlinkWrapper>
        <Headline className="mb0">Search</Headline>
        <HeadLineButtonWrapper>
          <Button
            type="primary"
            whiteSpace="nowrap"
            onClick={handleClickAskQuestionBtn}
          >
            Ask Question
          </Button>
        </HeadLineButtonWrapper>
      </HeadlinkWrapper>
      <SearchTips />
    </>
  );
};

export default PageHeader;
