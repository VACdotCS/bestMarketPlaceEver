"use client";

import { AdminPanelIcon } from "@/components/icons/admin-panel-icon";
import { PhoneIcon } from "@/components/icons/phone-icon";
import Modal from "@/components/layout/modal";
import { Button } from "@/components/ui/button";
import { Field } from "@/components/ui/field";
import { Select } from "@/components/ui/select";
import { useState } from "react";

const TestPage = ({}) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpenModal = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };
  return (
    <div className="flex flex-col gap-2">
      <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
        <div className="flex flex-col gap-10">
          <div className="flex flex-col gap-3">
            <h2 className="text-lg font-bold">Modal Title</h2>
            <p>This is the modal content.</p>
          </div>
          <Button onClick={handleCloseModal}>Close</Button>
        </div>
      </Modal>
      <Select options={["Опция 1", "Опция 2"]} size="small" />
      <Button onClick={handleOpenModal}>Open Modal</Button>
      <Button onClick={() => console.log("Яркая кнопка")}>Яркая кнопка</Button>
      <Button color="dark" onClick={() => console.log("Кнопка потемнее")}>
        Кнопка потемнее
      </Button>
      <Button
        variant="icon"
        icon={<AdminPanelIcon />}
        onClick={() => console.log("Кнопка-иконка")}
      />

      <Button variant="link" href="/">
        Ссылка
      </Button>

      <Field placeholder="Просто поле ввода" />
      <Field placeholder="Недоступно для ввода поле (disabled)" disabled />
      <Field
        placeholder="Просто поле ввода c неизменным контентом"
        startContent="₽"
      />
      <Field placeholder="Просто поле с иконкой" icon={<AdminPanelIcon />} />
      <Field
        placeholder="Просто поле с иконкой"
        icon={<PhoneIcon />}
        startContent="+7"
      />

      <Field variant="numeric" placeholder="500" startContent="₽" />
      <Field variant="numeric" placeholder="500" />

      <Field variant="textarea" placeholder="500" />
    </div>
  );
};

export default TestPage;
