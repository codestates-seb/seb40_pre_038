import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Headline } from '../../components/Headline';
import { Button } from '../../components/Buttons';

const PageHeaderWrapper = styled.div`
  display: flex !important;
  margin-bottom: 12px !important;
  flex-wrap: wrap !important;
`;

const ButtonContainer = styled.div`
  margin-bottom: 12px !important;
`;

const PageHeader = () => {
  const navigate = useNavigate();

  const handleClickAskQuestionBtn = () => {
    navigate('/questions/ask');
  };
  return (
    <PageHeaderWrapper>
      <Headline className="mr12 mb12">All Questions</Headline>
      <ButtonContainer>
        <Button
          type="primary"
          whiteSpace="nowrap"
          onClick={handleClickAskQuestionBtn}
        >
          Ask Question
        </Button>
      </ButtonContainer>
    </PageHeaderWrapper>
  );
};

export default PageHeader;
