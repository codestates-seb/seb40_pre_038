import React from "react";
import styled from "styled-components";

const Bluebutton = styled.button`
  background: #0995ff;
  color: #ffffff;
  font-weight: bold;
  border: 1px solid #0995ff;
  border-radius: 4px;
  box-shadow: inset 0 1px 0 0 #ffffff;
  width: ${(props) => props.width || "100px"};
  height: ${(props) => props.height || "40px"};
  font-size: ${(props) => props.fontSize || "14px"};
  :hover {
    background: #0063bf;
  }
`;

const SkyblueButton = styled.button`
  background: #e1ecf4;
  color: #3a739d;
  font-weight: bold;
  border-radius: 4px;
  border: ${(props) => props.border || "1px solid #3a739d"};
  width: ${(props) => props.width || "100px"};
  height: ${(props) => props.height || "40px"};
  font-size: ${(props) => props.fontSize || "14px"};
  :hover {
    background: #b3d3ea;
  }
`;

function ButtonBlue({ children, border, width, height, fontSize }) {
  return (
    <Bluebutton border={border} width={width} height={height} fontSize={fontSize}>
      {children}
    </Bluebutton>
  );
}

function ButtonSblue({ children, width, height, fontSize }) {
  return (
    <SkyblueButton width={width} height={height} fontSize={fontSize}>
      {children}
    </SkyblueButton>
  );
}

export { ButtonBlue, ButtonSblue };
