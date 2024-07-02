import Modal from "@/components/layout/modal";

interface PickupPointModalProps {
  isModalOpen: boolean;
  handleCloseModal: () => void;
  handleSelectAddress: (address: string) => void;
}

const availableAddresses = [
  { code: "КРД-001", address: "ул. Ставрпопольская, 149" },
  { code: "КРД-002", address: "ул. Ставрпопольская, 150" },
  { code: "КРД-003", address: "ул. Ставрпопольская, 151" },
  { code: "КРД-004", address: "ул. Ставрпопольская, 152" },
];

export const PickupPointModal = ({
  isModalOpen,
  handleCloseModal,
  handleSelectAddress,
}: PickupPointModalProps) => {
  return (
    <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
      <h3 className="mb-3 text-center text-lg font-semibold">Пункт выдачи</h3>
      <div className="flex flex-col gap-2">
        {availableAddresses.map((address) => (
          <div
            className="grid cursor-pointer grid-cols-8"
            key={address.code}
            onClick={() => handleSelectAddress(address.address)}
          >
            <div className="col-span-2 flex items-center justify-center rounded-s-md border-r border-accent bg-accent/20 py-2 text-lg font-semibold">
              {address.code}
            </div>
            <div className="col-span-6 flex items-center justify-center rounded-e-md bg-field-bg text-center">
              {address.address}
            </div>
          </div>
        ))}
      </div>
    </Modal>
  );
};
