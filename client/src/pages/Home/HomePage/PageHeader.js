import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Button } from '../../../components/Buttons';
import { Headline } from '../../../components/Headline';

const PageHeaderWrapper = styled.div`
  display: flex !important;
`;

const ButtonContainer = styled.div`
  margin-left: 12px !important;
`;

const PageHeader = () => {
  const navigate = useNavigate();

  const handleClickAskQuestionBtn = () => {
    navigate('/questions/ask');
  };

  return (
    <PageHeaderWrapper>
      <Headline>Top Questions</Headline>
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
